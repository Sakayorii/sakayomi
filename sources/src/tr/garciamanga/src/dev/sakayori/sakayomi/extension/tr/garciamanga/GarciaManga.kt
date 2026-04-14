package dev.sakayori.sakayomi.extension.tr.garciamanga

import dev.sakayori.sakayomi.multisrc.madara.Madara
import java.text.SimpleDateFormat
import java.util.Locale

class GarciaManga :
    Madara(
        "Garcia Manga",
        "https://garciamanga.com",
        "tr",
        dateFormat = SimpleDateFormat("MMMM d, yyyy", Locale("tr")),
    ) {
    override val useLoadMoreRequest = LoadMoreStrategy.Always
    override val useNewChapterEndpoint = true

    override val filterNonMangaItems = false
}
