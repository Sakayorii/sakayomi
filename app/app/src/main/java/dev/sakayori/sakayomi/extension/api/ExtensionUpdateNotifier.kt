package dev.sakayori.sakayomi.extension.api

import android.content.Context
import androidx.core.app.NotificationCompat
import dev.sakayori.sakayomi.R
import dev.sakayori.sakayomi.core.security.SecurityPreferences
import dev.sakayori.sakayomi.data.notification.NotificationReceiver
import dev.sakayori.sakayomi.data.notification.Notifications
import dev.sakayori.sakayomi.util.system.cancelNotification
import dev.sakayori.sakayomi.util.system.notify
import tachiyomi.core.common.i18n.pluralStringResource
import tachiyomi.i18n.MR
import uy.kohesive.injekt.Injekt
import uy.kohesive.injekt.api.get

class ExtensionUpdateNotifier(
    private val context: Context,
    private val securityPreferences: SecurityPreferences = Injekt.get(),
) {
    fun promptUpdates(names: List<String>) {
        context.notify(
            Notifications.ID_UPDATES_TO_EXTS,
            Notifications.CHANNEL_EXTENSIONS_UPDATE,
        ) {
            setContentTitle(
                context.pluralStringResource(
                    MR.plurals.update_check_notification_ext_updates,
                    names.size,
                    names.size,
                ),
            )
            if (!securityPreferences.hideNotificationContent.get()) {
                val extNames = names.joinToString(", ")
                setContentText(extNames)
                setStyle(NotificationCompat.BigTextStyle().bigText(extNames))
            }
            setSmallIcon(R.drawable.ic_extension_24dp)
            setContentIntent(NotificationReceiver.openExtensionsPendingActivity(context))
            setAutoCancel(true)
        }
    }

    fun dismiss() {
        context.cancelNotification(Notifications.ID_UPDATES_TO_EXTS)
    }
}
