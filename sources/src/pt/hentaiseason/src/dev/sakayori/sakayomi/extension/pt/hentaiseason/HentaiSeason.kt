package dev.sakayori.sakayomi.extension.pt.hentaiseason

import dev.sakayori.sakayomi.multisrc.gattsu.Gattsu
import dev.sakayori.sakayomi.network.interceptor.rateLimit
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit

class HentaiSeason :
    Gattsu(
        "Hentai Season",
        "https://hentaiseason.com",
        "pt-BR",
    ) {

    override val client: OkHttpClient = super.client.newBuilder()
        .rateLimit(1, 2, TimeUnit.SECONDS)
        .build()
}
