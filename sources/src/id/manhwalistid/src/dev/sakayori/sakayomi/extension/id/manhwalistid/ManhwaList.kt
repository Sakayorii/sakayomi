package dev.sakayori.sakayomi.extension.id.manhwalistid

import dev.sakayori.sakayomi.multisrc.mangathemesia.MangaThemesia
import dev.sakayori.sakayomi.network.interceptor.rateLimit
import okhttp3.OkHttpClient

class ManhwaList :
    MangaThemesia(
        "Manhwa List",
        "https://manhwalist02.site",
        "id",
    ) {
    override val client: OkHttpClient = super.client.newBuilder()
        .rateLimit(3)
        .build()
}
