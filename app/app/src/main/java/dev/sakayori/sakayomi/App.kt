package dev.sakayori.sakayomi

import android.annotation.SuppressLint
import android.app.Application
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import android.os.Looper
import android.webkit.WebView
import androidx.core.content.ContextCompat
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ProcessLifecycleOwner
import androidx.lifecycle.lifecycleScope
import coil3.ImageLoader
import coil3.SingletonImageLoader
import coil3.memory.MemoryCache
import coil3.network.okhttp.OkHttpNetworkFetcherFactory
import coil3.request.allowRgb565
import coil3.request.crossfade
import coil3.util.DebugLogger
import dev.sakayomi.injekt.patchInjekt
import dev.sakayori.domain.DomainModule
import dev.sakayori.domain.base.BasePreferences
import dev.sakayori.domain.ui.UiPreferences
import dev.sakayori.domain.ui.model.setAppCompatDelegateThemeMode
import dev.sakayori.sakayomi.core.security.PrivacyPreferences
import dev.sakayori.sakayomi.crash.CrashActivity
import dev.sakayori.sakayomi.crash.GlobalExceptionHandler
import dev.sakayori.sakayomi.data.coil.BufferedSourceFetcher
import dev.sakayori.sakayomi.data.coil.MangaCoverFetcher
import dev.sakayori.sakayomi.data.coil.MangaCoverKeyer
import dev.sakayori.sakayomi.data.coil.MangaKeyer
import dev.sakayori.sakayomi.data.coil.SakayomiImageDecoder
import dev.sakayori.sakayomi.data.notification.Notifications
import dev.sakayori.sakayomi.di.AppModule
import dev.sakayori.sakayomi.di.PreferenceModule
import dev.sakayori.sakayomi.network.NetworkHelper
import dev.sakayori.sakayomi.network.NetworkPreferences
import dev.sakayori.sakayomi.ui.base.delegate.SecureActivityDelegate
import dev.sakayori.sakayomi.util.system.DeviceUtil
import dev.sakayori.sakayomi.util.system.GLUtil
import dev.sakayori.sakayomi.util.system.WebViewUtil
import dev.sakayori.sakayomi.util.system.animatorDurationScale
import dev.sakayori.sakayomi.util.system.cancelNotification
import dev.sakayori.sakayomi.util.system.notify
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import logcat.AndroidLogcatLogger
import logcat.LogPriority
import logcat.LogcatLogger
import sakayomi.core.migration.Migrator
import sakayomi.core.migration.migrations.migrations
import sakayomi.telemetry.TelemetryConfig
import org.conscrypt.Conscrypt
import tachiyomi.core.common.i18n.stringResource
import tachiyomi.core.common.preference.Preference
import tachiyomi.core.common.preference.PreferenceStore
import tachiyomi.core.common.util.system.ImageUtil
import tachiyomi.core.common.util.system.logcat
import tachiyomi.i18n.MR
import tachiyomi.presentation.widget.WidgetManager
import uy.kohesive.injekt.Injekt
import uy.kohesive.injekt.api.get
import uy.kohesive.injekt.injectLazy
import java.security.Security

class App : Application(), DefaultLifecycleObserver, SingletonImageLoader.Factory {

    private val basePreferences: BasePreferences by injectLazy()
    private val privacyPreferences: PrivacyPreferences by injectLazy()
    private val networkPreferences: NetworkPreferences by injectLazy()

    private val disableIncognitoReceiver = DisableIncognitoReceiver()

    @SuppressLint("LaunchActivityFromNotification")
    override fun onCreate() {
        super<Application>.onCreate()
        patchInjekt()
        TelemetryConfig.init(applicationContext)

        GlobalExceptionHandler.initialize(applicationContext, CrashActivity::class.java)

        // TLS 1.3 support for Android < 10
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.Q) {
            Security.insertProviderAt(Conscrypt.newProvider(), 1)
        }

        // Avoid potential crashes
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            val process = getProcessName()
            if (packageName != process) WebView.setDataDirectorySuffix(process)
        }

        Injekt.importModule(PreferenceModule(this))
        Injekt.importModule(AppModule(this))
        Injekt.importModule(DomainModule())

        setupNotificationChannels()

        ProcessLifecycleOwner.get().lifecycle.addObserver(this)

        val scope = ProcessLifecycleOwner.get().lifecycleScope

        // Show notification to disable Incognito Mode when it's enabled
        basePreferences.incognitoMode.changes()
            .onEach { enabled ->
                if (enabled) {
                    disableIncognitoReceiver.register()
                    notify(
                        Notifications.ID_INCOGNITO_MODE,
                        Notifications.CHANNEL_INCOGNITO_MODE,
                    ) {
                        setContentTitle(stringResource(MR.strings.pref_incognito_mode))
                        setContentText(stringResource(MR.strings.notification_incognito_text))
                        setSmallIcon(R.drawable.ic_glasses_24dp)
                        setOngoing(true)

                        val pendingIntent = PendingIntent.getBroadcast(
                            this@App,
                            0,
                            Intent(ACTION_DISABLE_INCOGNITO_MODE).setPackage(BuildConfig.APPLICATION_ID),
                            PendingIntent.FLAG_ONE_SHOT or PendingIntent.FLAG_IMMUTABLE,
                        )
                        setContentIntent(pendingIntent)
                    }
                } else {
                    disableIncognitoReceiver.unregister()
                    cancelNotification(Notifications.ID_INCOGNITO_MODE)
                }
            }
            .launchIn(scope)

        privacyPreferences.analytics
            .changes()
            .onEach(TelemetryConfig::setAnalyticsEnabled)
            .launchIn(scope)

        privacyPreferences.crashlytics
            .changes()
            .onEach(TelemetryConfig::setCrashlyticsEnabled)
            .launchIn(scope)

        basePreferences.hardwareBitmapThreshold.let { preference ->
            if (!preference.isSet()) preference.set(GLUtil.DEVICE_TEXTURE_LIMIT)
        }

        basePreferences.hardwareBitmapThreshold.changes()
            .onEach { ImageUtil.hardwareBitmapThreshold = it }
            .launchIn(scope)

        setAppCompatDelegateThemeMode(Injekt.get<UiPreferences>().themeMode.get())

        // Updates widget update
        WidgetManager(Injekt.get(), Injekt.get()).apply { init(scope) }

        if (!LogcatLogger.isInstalled) {
            val minLogPriority = when {
                networkPreferences.verboseLogging.get() -> LogPriority.VERBOSE
                BuildConfig.DEBUG -> LogPriority.DEBUG
                else -> LogPriority.INFO
            }
            LogcatLogger.install()
            LogcatLogger.loggers += AndroidLogcatLogger(minLogPriority)
        }

        initializeMigrator()
    }

    private fun initializeMigrator() {
        val preferenceStore = Injekt.get<PreferenceStore>()
        val preference = preferenceStore.getInt(Preference.appStateKey("last_version_code"), 0)
        logcat { "Migration from ${preference.get()} to ${BuildConfig.VERSION_CODE}" }
        Migrator.initialize(
            old = preference.get(),
            new = BuildConfig.VERSION_CODE,
            migrations = migrations,
            onMigrationComplete = {
                logcat { "Updating last version to ${BuildConfig.VERSION_CODE}" }
                preference.set(BuildConfig.VERSION_CODE)
            },
        )
    }

    override fun newImageLoader(context: Context): ImageLoader {
        return ImageLoader.Builder(this).apply {
            val callFactoryLazy = lazy { Injekt.get<NetworkHelper>().client }
            components {
                // NetworkFetcher.Factory
                add(OkHttpNetworkFetcherFactory(callFactoryLazy::value))
                // Decoder.Factory
                add(SakayomiImageDecoder.Factory())
                // Fetcher.Factory
                add(BufferedSourceFetcher.Factory())
                add(MangaCoverFetcher.MangaCoverFactory(callFactoryLazy))
                add(MangaCoverFetcher.MangaFactory(callFactoryLazy))
                // Keyer
                add(MangaCoverKeyer())
                add(MangaKeyer())
            }

            memoryCache(
                MemoryCache.Builder()
                    .maxSizePercent(context)
                    .build(),
            )

            crossfade((300 * this@App.animatorDurationScale).toInt())
            allowRgb565(DeviceUtil.isLowRamDevice(this@App))
            if (networkPreferences.verboseLogging.get()) logger(DebugLogger())

            // Coil spawns a new thread for every image load by default
            fetcherCoroutineContext(Dispatchers.IO.limitedParallelism(8))
            decoderCoroutineContext(Dispatchers.IO.limitedParallelism(3))
        }
            .build()
    }

    override fun onStart(owner: LifecycleOwner) {
        SecureActivityDelegate.onApplicationStart()
    }

    override fun onStop(owner: LifecycleOwner) {
        SecureActivityDelegate.onApplicationStopped()
    }

    override fun getPackageName(): String {
        try {
            // Override the value passed as X-Requested-With in WebView requests
            val stackTrace = Looper.getMainLooper().thread.stackTrace
            val isChromiumCall = stackTrace.any { trace ->
                trace.className.lowercase() in setOf("org.chromium.base.buildinfo", "org.chromium.base.apkinfo") &&
                    trace.methodName.lowercase() in setOf("getall", "getpackagename", "<init>")
            }

            if (isChromiumCall) return WebViewUtil.spoofedPackageName(applicationContext)
        } catch (_: Exception) {
        }

        return super.getPackageName()
    }

    private fun setupNotificationChannels() {
        try {
            Notifications.createChannels(this)
        } catch (e: Exception) {
            logcat(LogPriority.ERROR, e) { "Failed to modify notification channels" }
        }
    }

    private inner class DisableIncognitoReceiver : BroadcastReceiver() {
        private var registered = false

        override fun onReceive(context: Context, intent: Intent) {
            basePreferences.incognitoMode.set(false)
        }

        fun register() {
            if (!registered) {
                ContextCompat.registerReceiver(
                    this@App,
                    this,
                    IntentFilter(ACTION_DISABLE_INCOGNITO_MODE),
                    ContextCompat.RECEIVER_NOT_EXPORTED,
                )
                registered = true
            }
        }

        fun unregister() {
            if (registered) {
                unregisterReceiver(this)
                registered = false
            }
        }
    }
}

private const val ACTION_DISABLE_INCOGNITO_MODE = "tachi.action.DISABLE_INCOGNITO_MODE"
