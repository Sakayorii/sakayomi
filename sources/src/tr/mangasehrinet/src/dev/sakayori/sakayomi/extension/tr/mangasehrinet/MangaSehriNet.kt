package dev.sakayori.sakayomi.extension.tr.mangasehrinet

import dev.sakayori.sakayomi.multisrc.madara.Madara
import java.text.SimpleDateFormat
import java.util.Locale

class MangaSehriNet :
    Madara(
        "Manga Şehri.net",
        "https://manga-sehri.net",
        "tr",
        dateFormat = SimpleDateFormat("d MMMM yyyy", Locale("tr")),
    ) {
    override val useLoadMoreRequest = LoadMoreStrategy.AutoDetect
    override val useNewChapterEndpoint = false
}
