package dev.sakayori.sakayomi.extension.tr.turkcemangaoku

import dev.sakayori.sakayomi.multisrc.madara.Madara
import java.text.SimpleDateFormat
import java.util.Locale

class TurkceMangaOku :
    Madara(
        "Türkçe Manga Oku",
        "https://trmangaoku.com",
        "tr",
        dateFormat = SimpleDateFormat("d MMMM yyyy", Locale("tr")),
    ) {
    override val mangaDetailsSelectorStatus = "div.summary-heading:contains(Durumu) + div.summary-content"

    override val useLoadMoreRequest = LoadMoreStrategy.Always

    override val useNewChapterEndpoint = true
}
