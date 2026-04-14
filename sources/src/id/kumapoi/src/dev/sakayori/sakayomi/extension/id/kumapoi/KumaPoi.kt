package dev.sakayori.sakayomi.extension.id.kumapoi

import dev.sakayori.sakayomi.multisrc.mangathemesia.MangaThemesia
import dev.sakayori.sakayomi.network.interceptor.rateLimit
import okhttp3.OkHttpClient

class KumaPoi : MangaThemesia("KumaPoi", "https://kumapoi.info", "id") {
    override val client: OkHttpClient = super.client.newBuilder()
        .rateLimit(4)
        .build()

    override val hasProjectPage = true
}
