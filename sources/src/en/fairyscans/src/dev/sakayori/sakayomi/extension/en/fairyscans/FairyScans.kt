package dev.sakayori.sakayomi.extension.en.fairyscans

import android.content.SharedPreferences
import androidx.preference.PreferenceScreen
import dev.sakayori.sakayomi.multisrc.mangathemesia.MangaThemesia
import dev.sakayori.sakayomi.multisrc.mangathemesia.MangaThemesiaPaidChapterHelper
import dev.sakayori.sakayomi.network.interceptor.rateLimit
import dev.sakayori.sakayomi.source.ConfigurableSource
import Sakayorii.utils.getPreferences
import okhttp3.OkHttpClient

class FairyScans :
    MangaThemesia("Fairy Scans", "https://fairyscans.com", "en"),
    ConfigurableSource {

    override val client: OkHttpClient = super.client.newBuilder()
        .rateLimit(2)
        .build()

    private val preferences: SharedPreferences = getPreferences()

    private val paidChapterHelper = MangaThemesiaPaidChapterHelper()

    override fun chapterListSelector(): String = paidChapterHelper.getChapterListSelectorBasedOnHidePaidChaptersPref(
        super.chapterListSelector(),
        preferences,
    )

    override fun setupPreferenceScreen(screen: PreferenceScreen) {
        paidChapterHelper.addHidePaidChaptersPreferenceToScreen(screen, intl)
    }
}
