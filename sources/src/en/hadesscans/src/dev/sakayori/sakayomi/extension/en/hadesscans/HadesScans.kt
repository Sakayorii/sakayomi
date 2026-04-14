package dev.sakayori.sakayomi.extension.en.hadesscans

import android.content.SharedPreferences
import androidx.preference.PreferenceScreen
import dev.sakayori.sakayomi.multisrc.mangathemesia.MangaThemesia
import dev.sakayori.sakayomi.multisrc.mangathemesia.MangaThemesiaPaidChapterHelper
import dev.sakayori.sakayomi.network.interceptor.rateLimit
import dev.sakayori.sakayomi.source.ConfigurableSource
import dev.sakayori.sakayomi.source.model.SManga
import Sakayorii.utils.getPreferences
import okhttp3.OkHttpClient
import org.jsoup.nodes.Element
import java.text.SimpleDateFormat
import java.util.Locale

class HadesScans :
    MangaThemesia(
        name = "Hades Scans",
        baseUrl = "https://hadesscans.com",
        lang = "en",
        dateFormat = SimpleDateFormat("MMMM d, yyyy", Locale.ENGLISH),
    ),
    ConfigurableSource {

    override val client: OkHttpClient = super.client.newBuilder()
        .rateLimit(2)
        .build()

    private val preferences: SharedPreferences = getPreferences()

    private val paidChapterHelper = MangaThemesiaPaidChapterHelper(lockedChapterSelector = ".locked-badge")

    override fun chapterListSelector(): String = paidChapterHelper.getChapterListSelectorBasedOnHidePaidChaptersPref(
        super.chapterListSelector(),
        preferences,
    )

    override fun searchMangaSelector() = ".listupd .bsx"

    override fun searchMangaFromElement(element: Element): SManga = super.searchMangaFromElement(element).apply {
        title = element.select("h4.tt").text()
    }

    override val seriesTitleSelector = ".manga-title"
    override val seriesAltNameSelector = ".manga-alt"
    override val seriesAuthorSelector = ".meta-row span:has(i.fa-user-edit)"
    override val seriesDescriptionSelector = ".entry-content"
    override val seriesGenreSelector = ".genre-list a"
    override val seriesStatusSelector = ".meta-row span:has(.status-tag)"
    override val seriesThumbnailSelector = ".info-poster img"

    override fun setupPreferenceScreen(screen: PreferenceScreen) {
        paidChapterHelper.addHidePaidChaptersPreferenceToScreen(
            screen,
            intl,
        )
    }
}
