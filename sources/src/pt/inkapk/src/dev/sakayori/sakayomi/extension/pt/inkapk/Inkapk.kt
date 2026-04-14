package dev.sakayori.sakayomi.extension.pt.inkapk

import dev.sakayori.sakayomi.multisrc.madara.Madara
import dev.sakayori.sakayomi.network.interceptor.rateLimit
import java.text.SimpleDateFormat
import java.util.Locale

class Inkapk :
    Madara(
        "Inkapk",
        "https://inkapk.net",
        "pt-BR",
        SimpleDateFormat("MM dd, yyyy", Locale("pt", "BR")),
    ) {
    override val client = super.client.newBuilder()
        .rateLimit(2)
        .build()

    override val mangaSubString = "obras"

    override val useLoadMoreRequest = LoadMoreStrategy.Never

    override val useNewChapterEndpoint = true
}
