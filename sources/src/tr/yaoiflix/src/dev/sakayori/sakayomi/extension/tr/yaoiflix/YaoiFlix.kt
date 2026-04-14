package dev.sakayori.sakayomi.extension.tr.yaoiflix

import dev.sakayori.sakayomi.multisrc.madara.Madara
import dev.sakayori.sakayomi.network.interceptor.rateLimit
import java.text.SimpleDateFormat
import java.util.Locale

class YaoiFlix :
    Madara(
        "Yaoi Flix",
        "https://yaoiflix.lat",
        "tr",
        SimpleDateFormat("MMMM dd, yyyy", Locale("tr")),
    ) {
    override val client = super.client.newBuilder()
        .rateLimit(3)
        .build()

    override val useNewChapterEndpoint = true

    override val useLoadMoreRequest = LoadMoreStrategy.Never
}
