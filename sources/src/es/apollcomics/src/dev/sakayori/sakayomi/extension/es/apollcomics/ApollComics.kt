package dev.sakayori.sakayomi.extension.es.apollcomics

import dev.sakayori.sakayomi.multisrc.madara.Madara
import java.text.SimpleDateFormat
import java.util.Locale

class ApollComics :
    Madara(
        "ApollComics",
        "https://apollcomics.es",
        "es",
        SimpleDateFormat("MMMM d, yyyy", Locale("es")),
    ) {
    override val useLoadMoreRequest = LoadMoreStrategy.Never
    override val useNewChapterEndpoint = false
}
