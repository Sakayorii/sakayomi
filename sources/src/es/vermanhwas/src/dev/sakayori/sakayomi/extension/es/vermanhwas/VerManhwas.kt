package dev.sakayori.sakayomi.extension.es.vermanhwas

import dev.sakayori.sakayomi.multisrc.madara.Madara
import dev.sakayori.sakayomi.network.GET
import okhttp3.Request
import java.text.SimpleDateFormat
import java.util.Locale

class VerManhwas :
    Madara(
        "Ver Manhwas",
        "https://vermanhwa.com",
        "es",
        dateFormat = SimpleDateFormat("MMMM d, yyyy", Locale("es")),
    ) {
    override val useLoadMoreRequest = LoadMoreStrategy.Never
    override val useNewChapterEndpoint = false

    override fun genresRequest(): Request = GET("$baseUrl/?s=&post_type=wp-manga", headers)
}
