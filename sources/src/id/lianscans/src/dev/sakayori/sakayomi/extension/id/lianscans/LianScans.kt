package dev.sakayori.sakayomi.extension.id.lianscans

import dev.sakayori.sakayomi.multisrc.mangathemesia.MangaThemesia
import dev.sakayori.sakayomi.network.interceptor.rateLimit
import okhttp3.OkHttpClient

class LianScans : MangaThemesia("LianScans", "https://www.lianscans.com", "id") {

    override val client: OkHttpClient = super.client.newBuilder()
        .rateLimit(4)
        .build()

    override val hasProjectPage = true
}
