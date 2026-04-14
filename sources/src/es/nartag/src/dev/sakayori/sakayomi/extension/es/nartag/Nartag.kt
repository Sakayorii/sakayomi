package dev.sakayori.sakayomi.extension.es.nartag

import dev.sakayori.sakayomi.multisrc.madara.Madara
import dev.sakayori.sakayomi.network.interceptor.rateLimitHost
import okhttp3.HttpUrl.Companion.toHttpUrl
import okhttp3.OkHttpClient
import java.text.SimpleDateFormat
import java.util.Locale

class Nartag :
    Madara(
        "Traducciones Amistosas",
        "https://nartag.com",
        "es",
        dateFormat = SimpleDateFormat("MMMM dd, yyyy", Locale("es")),
    ) {
    override val versionId = 2

    override val client: OkHttpClient = super.client.newBuilder()
        .rateLimitHost(baseUrl.toHttpUrl(), 2)
        .build()

    override val useNewChapterEndpoint = true

    override val useLoadMoreRequest = LoadMoreStrategy.Never
}
