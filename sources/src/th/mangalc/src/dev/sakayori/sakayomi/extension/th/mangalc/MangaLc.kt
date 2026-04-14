package dev.sakayori.sakayomi.extension.th.mangalc

import dev.sakayori.sakayomi.multisrc.madara.Madara
import java.text.SimpleDateFormat
import java.util.Locale

class MangaLc :
    Madara(
        "Manga-Lc",
        "https://manga-lc.net",
        "th",
        dateFormat = SimpleDateFormat("d MMMM yyyy", Locale("th")),
    ) {
    override val pageListParseSelector = ".reading-content img"

    override val useLoadMoreRequest = LoadMoreStrategy.Never
    override val useNewChapterEndpoint = false

    override val filterNonMangaItems = false
}
