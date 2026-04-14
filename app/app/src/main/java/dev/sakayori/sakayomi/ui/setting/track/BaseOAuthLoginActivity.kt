package dev.sakayori.sakayomi.ui.setting.track

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import dev.sakayori.sakayomi.data.track.TrackerManager
import dev.sakayori.sakayomi.ui.base.activity.BaseActivity
import dev.sakayori.sakayomi.ui.main.MainActivity
import dev.sakayori.sakayomi.util.view.setComposeContent
import tachiyomi.presentation.core.screens.LoadingScreen
import uy.kohesive.injekt.injectLazy

abstract class BaseOAuthLoginActivity : BaseActivity() {

    internal val trackerManager: TrackerManager by injectLazy()

    abstract fun handleResult(uri: Uri)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setComposeContent {
            LoadingScreen()
        }

        val data = intent.data
        if (data == null) {
            returnToSettings()
        } else {
            handleResult(data)
        }
    }

    internal fun returnToSettings() {
        finish()

        val intent = Intent(this, MainActivity::class.java).apply {
            addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP)
        }
        startActivity(intent)
    }
}
