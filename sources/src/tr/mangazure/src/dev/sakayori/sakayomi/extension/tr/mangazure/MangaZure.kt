package dev.sakayori.sakayomi.extension.tr.mangazure

import dev.sakayori.sakayomi.multisrc.madara.Madara
import java.text.SimpleDateFormat
import java.util.Locale

class MangaZure :
    Madara(
        "MangaZure",
        "https://mangazure.net",
        "tr",
        dateFormat = SimpleDateFormat("dd MMMM yyyy", Locale("tr")),
    ) {
    override val useLoadMoreRequest = LoadMoreStrategy.Always
    override val useNewChapterEndpoint = false
}
