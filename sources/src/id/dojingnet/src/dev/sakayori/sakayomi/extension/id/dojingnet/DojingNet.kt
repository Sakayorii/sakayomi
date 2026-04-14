package dev.sakayori.sakayomi.extension.id.dojingnet

import dev.sakayori.sakayomi.multisrc.mangathemesia.MangaThemesia
import dev.sakayori.sakayomi.network.interceptor.rateLimit
import okhttp3.OkHttpClient

class DojingNet : MangaThemesia("Dojing.net", "https://dojing.net", "id") {

    override val client: OkHttpClient = super.client.newBuilder()
        .rateLimit(4)
        .build()

    override val hasProjectPage = true
}
