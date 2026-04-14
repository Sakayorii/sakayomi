package dev.sakayori.sakayomi.extension.en.culturedworks

import dev.sakayori.sakayomi.multisrc.mangathemesia.MangaThemesia
import dev.sakayori.sakayomi.network.GET
import dev.sakayori.sakayomi.network.interceptor.rateLimit
import dev.sakayori.sakayomi.source.model.Page
import okhttp3.HttpUrl.Companion.toHttpUrl
import okhttp3.Request

class CulturedWorks :
    MangaThemesia(
        "CulturedWorks",
        "https://culturedworks.com",
        "en",
    ) {
    override val client = super.client.newBuilder()
        .rateLimit(2)
        .build()

    override val seriesDetailsSelector = ".main-info"
    override val seriesStatusSelector = ".info-right .status, ${super.seriesStatusSelector}"
    override val seriesGenreSelector = ".meta .genres .genre-item"

    override fun imageRequest(page: Page): Request {
        val host = page.imageUrl!!.toHttpUrl().host

        val headers = headersBuilder().apply {
            add("Accept", "image/webp,image/apng,image/svg+xml,image/*,*/*;q=0.8")
            add("Host", host)
            if (host.contains("kumacdn")) { // This doesn't load on the website, but removing referer seems to fix it
                removeAll("Referer")
            } else {
                set("Referer", "$baseUrl/")
            }
        }.build()

        return GET(page.imageUrl!!, headers)
    }
}
