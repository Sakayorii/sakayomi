package dev.sakayori.sakayomi.extension.es.topcomicporno

import dev.sakayori.sakayomi.multisrc.madara.Madara
import java.text.SimpleDateFormat
import java.util.Locale

class TopComicPorno :
    Madara(
        "TopComicPorno",
        "https://topcomicporno.com",
        "es",
        dateFormat = SimpleDateFormat("MMM dd, yy", Locale("es")),
    ) {
    override val useLoadMoreRequest = LoadMoreStrategy.Never
    override val useNewChapterEndpoint = true
}
