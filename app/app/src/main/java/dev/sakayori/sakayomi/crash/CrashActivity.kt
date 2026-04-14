package dev.sakayori.sakayomi.crash

import android.content.Intent
import android.os.Bundle
import androidx.core.view.WindowCompat
import dev.sakayori.presentation.crash.CrashScreen
import dev.sakayori.sakayomi.ui.base.activity.BaseActivity
import dev.sakayori.sakayomi.ui.main.MainActivity
import dev.sakayori.sakayomi.util.view.setComposeContent

class CrashActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, false)

        val exception = GlobalExceptionHandler.getThrowableFromIntent(intent)
        setComposeContent {
            CrashScreen(
                exception = exception,
                onRestartClick = {
                    finishAffinity()
                    startActivity(Intent(this@CrashActivity, MainActivity::class.java))
                },
            )
        }
    }
}
