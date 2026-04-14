package dev.sakayori.sakayomi.extension.es.asialotus

import dev.sakayori.sakayomi.multisrc.mangathemesia.MangaThemesia
import dev.sakayori.sakayomi.network.interceptor.rateLimit

class AsiaLotus :
    MangaThemesia(
        "Asia Lotus",
        "https://asialotuss.com",
        "es",
    ) {
    override val client = super.client.newBuilder()
        .rateLimit(3)
        .build()
}
