package dev.sakayori.sakayomi.extension.id.komikstation

import dev.sakayori.sakayomi.multisrc.mangathemesia.MangaThemesia
import dev.sakayori.sakayomi.network.interceptor.rateLimit
import okhttp3.OkHttpClient

class KomikStation : MangaThemesia("Komik Station", "https://komikstation.org", "id") {
    // Formerly "Komik Station (WP Manga Stream)"
    override val id = 6148605743576635261

    override val client: OkHttpClient = super.client.newBuilder()
        .rateLimit(4)
        .build()

    override val projectPageString = "/project-list"

    override val hasProjectPage = true
}
