package dev.sakayori.sakayomi.extension.es.stickhorse

import dev.sakayori.sakayomi.multisrc.madara.Madara
import java.text.SimpleDateFormat
import java.util.Locale

class StickHorse :
    Madara(
        "Stick Horse",
        "https://stickhorse.cl",
        "es",
        dateFormat = SimpleDateFormat("MMMM d, yyyy", Locale("es")),
    ) {
    override val useLoadMoreRequest = LoadMoreStrategy.Always
    override val useNewChapterEndpoint = false
}
