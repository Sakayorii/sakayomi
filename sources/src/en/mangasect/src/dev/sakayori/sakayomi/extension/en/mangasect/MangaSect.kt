package dev.sakayori.sakayomi.extension.en.mangasect

import dev.sakayori.sakayomi.multisrc.liliana.Liliana
import dev.sakayori.sakayomi.network.interceptor.rateLimit

class MangaSect :
    Liliana(
        "Manga Sect",
        "https://mangasect.net",
        "en",
        usesPostSearch = true,
    ) {
    override val client = super.client.newBuilder()
        .rateLimit(1)
        .build()
}
