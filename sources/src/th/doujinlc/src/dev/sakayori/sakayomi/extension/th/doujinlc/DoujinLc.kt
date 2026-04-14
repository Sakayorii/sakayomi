package dev.sakayori.sakayomi.extension.th.doujinlc

import dev.sakayori.sakayomi.multisrc.madara.Madara
import java.text.SimpleDateFormat
import java.util.Locale

class DoujinLc :
    Madara(
        "Doujin-Lc",
        "https://doujin-lc.net",
        "th",
        dateFormat = SimpleDateFormat("MMMM d, yyyy", Locale("th")),
    ) {
    override val pageListParseSelector = ".reading-content img"

    override val useLoadMoreRequest = LoadMoreStrategy.Never
    override val useNewChapterEndpoint = false

    override val mangaSubString = "doujin"
    override val filterNonMangaItems = false
}
