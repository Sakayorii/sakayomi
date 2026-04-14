package dev.sakayori.sakayomi.extension.en.sitemanga

import dev.sakayori.sakayomi.multisrc.madara.Madara
import java.text.SimpleDateFormat
import java.util.Locale

class SiteManga :
    Madara(
        "Site Manga",
        "https://sitemanga.com",
        "en",
        dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.US),
    ) {
    override val useNewChapterEndpoint = true

    override val useLoadMoreRequest = LoadMoreStrategy.Always

    override val filterNonMangaItems = false
}
