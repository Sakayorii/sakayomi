package dev.sakayori.sakayomi.extension.pl.mangahona

import dev.sakayori.sakayomi.multisrc.madara.Madara
import java.text.SimpleDateFormat
import java.util.Locale

class MangaHoNa :
    Madara(
        "MangaHoNa",
        "https://mangahona.pl",
        "pl",
        dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.ROOT),
    ) {
    override val useLoadMoreRequest = LoadMoreStrategy.Always
    override val useNewChapterEndpoint = true
}
