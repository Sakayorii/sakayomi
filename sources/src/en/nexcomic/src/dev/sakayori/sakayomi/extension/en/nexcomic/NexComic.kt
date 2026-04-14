package dev.sakayori.sakayomi.extension.en.nexcomic

import android.content.SharedPreferences
import androidx.preference.PreferenceScreen
import dev.sakayori.sakayomi.multisrc.mangathemesia.MangaThemesia
import dev.sakayori.sakayomi.multisrc.mangathemesia.MangaThemesiaPaidChapterHelper
import dev.sakayori.sakayomi.source.ConfigurableSource
import Sakayorii.utils.getPreferences

class NexComic :
    MangaThemesia("NexComic", "https://nexcomic.com", "en"),
    ConfigurableSource {

    private val preferences: SharedPreferences = getPreferences()

    private val paidChapterHelper = MangaThemesiaPaidChapterHelper(
        lockedChapterSelector = ".text-gold",
    )

    override fun chapterListSelector(): String {
        // Default selector is too broad; it picks up comment <li> as chapters
        val base = "#chapterlist li[data-num]"

        return paidChapterHelper.getChapterListSelectorBasedOnHidePaidChaptersPref(
            base,
            preferences,
        )
    }

    override fun setupPreferenceScreen(screen: PreferenceScreen) {
        paidChapterHelper.addHidePaidChaptersPreferenceToScreen(screen, intl)
    }
}
