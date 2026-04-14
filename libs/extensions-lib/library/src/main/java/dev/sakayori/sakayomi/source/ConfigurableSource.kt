package dev.sakayori.sakayomi.source

import androidx.preference.PreferenceScreen

@Suppress("unused")
interface ConfigurableSource {

    fun setupPreferenceScreen(screen: PreferenceScreen)

}
