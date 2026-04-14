package dev.sakayori.sakayomi.extension.en.comickiba

import dev.sakayori.sakayomi.multisrc.liliana.Liliana
import dev.sakayori.sakayomi.network.interceptor.rateLimit

class Manhuagold :
    Liliana(
        "Manhuagold",
        "https://manhuagold.top",
        "en",
        usesPostSearch = true,
    ) {
    // MangaReader -> Liliana
    override val versionId = 2

    override val client = super.client.newBuilder()
        .rateLimit(2)
        .build()
}
