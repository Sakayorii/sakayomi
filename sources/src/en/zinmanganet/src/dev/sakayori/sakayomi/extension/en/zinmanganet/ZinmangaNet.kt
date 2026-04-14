package dev.sakayori.sakayomi.extension.en.zinmanganet

import dev.sakayori.sakayomi.multisrc.madara.Madara
import java.text.SimpleDateFormat
import java.util.Locale

class ZinmangaNet :
    Madara(
        "Zinmanga.net",
        "https://zinmanga.net",
        "en",
        dateFormat = SimpleDateFormat("MM/dd/yyyy", Locale.ROOT),
    ) {
    override val useLoadMoreRequest = LoadMoreStrategy.Never
    override val useNewChapterEndpoint = false

    override val filterNonMangaItems = false
}
