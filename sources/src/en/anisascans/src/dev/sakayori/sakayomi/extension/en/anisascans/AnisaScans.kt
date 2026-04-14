package dev.sakayori.sakayomi.extension.en.anisascans

import dev.sakayori.sakayomi.multisrc.madara.Madara
import dev.sakayori.sakayomi.network.interceptor.rateLimit

class AnisaScans :
    Madara(
        "Anisa Scans",
        "https://anisascans.in",
        "en",
    ) {
    override val client = super.client.newBuilder()
        .rateLimit(3)
        .build()

    override val useLoadMoreRequest = LoadMoreStrategy.Never

    override val useNewChapterEndpoint = true
}
