package dev.sakayori.sakayomi.extension.en.athreascans

import android.content.SharedPreferences
import androidx.preference.PreferenceScreen
import dev.sakayori.sakayomi.multisrc.mangathemesia.MangaThemesia
import dev.sakayori.sakayomi.multisrc.mangathemesia.MangaThemesiaPaidChapterHelper
import dev.sakayori.sakayomi.network.interceptor.rateLimit
import dev.sakayori.sakayomi.source.ConfigurableSource
import dev.sakayori.sakayomi.source.model.SChapter
import Sakayorii.utils.getPreferences
import okhttp3.OkHttpClient
import okhttp3.Response

class AthreaScans :
    MangaThemesia("Athrea Scans", "https://athreascans.com", "en"),
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

    override fun chapterListParse(response: Response): List<SChapter> = super.chapterListParse(response).filterNot { chapter ->
        // Additional filter: skip chapters without valid URLs (locked chapters have no href)
        chapter.url.isBlank() || chapter.url == "#"
    }

    override fun setupPreferenceScreen(screen: PreferenceScreen) {
        paidChapterHelper.addHidePaidChaptersPreferenceToScreen(screen, intl)
    }
}
