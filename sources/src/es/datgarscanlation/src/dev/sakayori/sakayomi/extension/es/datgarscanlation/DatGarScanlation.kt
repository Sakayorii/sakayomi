package dev.sakayori.sakayomi.extension.es.datgarscanlation

import dev.sakayori.sakayomi.multisrc.zeistmanga.ZeistManga
import dev.sakayori.sakayomi.network.interceptor.rateLimit

class DatGarScanlation :
    ZeistManga(
        "Dat-Gar Scan",
        "https://datgarscanlation.blogspot.com",
        "es",
    ) {
    override val useNewChapterFeed = true
    override val hasFilters = true
    override val hasLanguageFilter = false

    override val client = super.client.newBuilder()
        .rateLimit(2)
        .build()
}
