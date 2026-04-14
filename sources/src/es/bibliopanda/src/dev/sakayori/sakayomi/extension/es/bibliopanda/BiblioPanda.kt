package dev.sakayori.sakayomi.extension.es.bibliopanda

import dev.sakayori.sakayomi.multisrc.madara.Madara
import dev.sakayori.sakayomi.network.interceptor.rateLimit
import java.text.SimpleDateFormat
import java.util.Locale

class BiblioPanda :
    Madara(
        "Biblio Panda",
        "https://bibliopanda.com",
        "es",
        SimpleDateFormat("MMMM dd, yyyy", Locale("es")),
    ) {
    override val client = super.client.newBuilder()
        .rateLimit(3)
        .build()

    override val useNewChapterEndpoint = true

    override val useLoadMoreRequest = LoadMoreStrategy.Never
}
