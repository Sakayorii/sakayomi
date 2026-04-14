package dev.sakayori.sakayomi.extension.es.lectormonline

import dev.sakayori.sakayomi.multisrc.lectormonline.LectorMOnline
import dev.sakayori.sakayomi.network.interceptor.rateLimit
import java.util.concurrent.TimeUnit

class LectorMOnline :
    LectorMOnline(
        name = "Lector MOnline",
        baseUrl = "https://www.lectormangas.online",
        lang = "es",
    ) {
    override val client = network.cloudflareClient.newBuilder()
        .rateLimit(3, 1, TimeUnit.SECONDS)
        .build()
}
