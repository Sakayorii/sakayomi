package dev.sakayori.sakayomi.ui.base.activity

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dev.sakayori.sakayomi.ui.base.delegate.SecureActivityDelegate
import dev.sakayori.sakayomi.ui.base.delegate.SecureActivityDelegateImpl
import dev.sakayori.sakayomi.ui.base.delegate.ThemingDelegate
import dev.sakayori.sakayomi.ui.base.delegate.ThemingDelegateImpl
import dev.sakayori.sakayomi.util.system.prepareTabletUiContext

open class BaseActivity :
    AppCompatActivity(),
    SecureActivityDelegate by SecureActivityDelegateImpl(),
    ThemingDelegate by ThemingDelegateImpl() {

    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(newBase.prepareTabletUiContext())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        applyAppTheme(this)
        super.onCreate(savedInstanceState)
    }
}
