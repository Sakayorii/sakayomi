package dev.sakayori.sakayomi.extension.pt.hentaitokyo

import dev.sakayori.sakayomi.multisrc.gattsu.Gattsu
import dev.sakayori.sakayomi.network.interceptor.rateLimit
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit

class HentaiTokyo :
    Gattsu(
        "Hentai Tokyo",
        "https://hentaitokyo.net",
        "pt-BR",
    ) {

    override val client: OkHttpClient = super.client.newBuilder()
        .rateLimit(1, 2, TimeUnit.SECONDS)
        .build()
}
