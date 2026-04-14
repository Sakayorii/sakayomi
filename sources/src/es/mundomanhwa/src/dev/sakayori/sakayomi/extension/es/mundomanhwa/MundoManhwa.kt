package dev.sakayori.sakayomi.extension.es.mundomanhwa

import dev.sakayori.sakayomi.multisrc.madara.Madara
import java.text.SimpleDateFormat
import java.util.Locale

class MundoManhwa :
    Madara(
        "Mundo Manhwa",
        "https://mundomanhwa.com",
        "es",
        dateFormat = SimpleDateFormat("MMMM d, yyyy", Locale("es")),
    ) {
    override val useLoadMoreRequest = LoadMoreStrategy.Never
    override val useNewChapterEndpoint = false
}
