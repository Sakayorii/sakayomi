package dev.sakayori.sakayomi.extension.tr.yaoimangaoku

import dev.sakayori.sakayomi.multisrc.madara.Madara
import java.text.SimpleDateFormat
import java.util.Locale

class YaoiMangaOku :
    Madara(
        "Yaoi Manga Oku",
        "https://yaoimangaoku.net",
        "tr",
        dateFormat = SimpleDateFormat("d MMMM yyyy", Locale("tr")),
    ) {
    override val useLoadMoreRequest = LoadMoreStrategy.Always
    override val useNewChapterEndpoint = false
}
