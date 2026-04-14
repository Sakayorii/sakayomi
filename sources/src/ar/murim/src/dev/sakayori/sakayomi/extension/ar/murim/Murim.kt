package dev.sakayori.sakayomi.extension.ar.murim

import dev.sakayori.sakayomi.multisrc.zeistmanga.ZeistManga
import dev.sakayori.sakayomi.network.interceptor.rateLimit
import okhttp3.Response

class Murim :
    ZeistManga(
        "Murim",
        "https://www.murim.site",
        "ar",
    ) {
    override val client = super.client.newBuilder()
        .rateLimit(3)
        .build()

    // Missing popular
    override val supportsLatest = false
    override fun popularMangaRequest(page: Int) = latestUpdatesRequest(page)
    override fun popularMangaParse(response: Response) = latestUpdatesParse(response)
}
