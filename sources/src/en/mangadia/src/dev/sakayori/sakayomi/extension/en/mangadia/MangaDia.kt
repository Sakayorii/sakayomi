package dev.sakayori.sakayomi.extension.en.mangadia

import dev.sakayori.sakayomi.multisrc.madara.Madara
import java.text.SimpleDateFormat
import java.util.Locale

class MangaDia :
    Madara(
        "MangaDia",
        "https://mangadia.com",
        "en",
        SimpleDateFormat("dd MMM yyyy", Locale("tr")),
    ) {
    override val useLoadMoreRequest = LoadMoreStrategy.Never
    override val useNewChapterEndpoint = true
}
