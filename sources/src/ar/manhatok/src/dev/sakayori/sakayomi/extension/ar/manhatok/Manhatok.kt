package dev.sakayori.sakayomi.extension.ar.manhatok

import dev.sakayori.sakayomi.multisrc.zeistmanga.ZeistManga
import dev.sakayori.sakayomi.network.interceptor.rateLimit

class Manhatok : ZeistManga("Manhatok", "https://manhatok.blogspot.com", "ar") {
    override val client = super.client.newBuilder()
        .rateLimit(2)
        .build()
}
