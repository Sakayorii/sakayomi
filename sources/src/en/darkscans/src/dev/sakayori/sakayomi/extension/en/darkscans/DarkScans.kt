package dev.sakayori.sakayomi.extension.en.darkscans

import dev.sakayori.sakayomi.multisrc.madara.Madara
import dev.sakayori.sakayomi.network.interceptor.rateLimit
import okhttp3.OkHttpClient

class DarkScans : Madara("Dark Scans", "https://darkscans.net", "en") {

    override val client: OkHttpClient = super.client.newBuilder()
        .rateLimit(20, 4)
        .build()

    override val mangaSubString = "mangas"

    override val useLoadMoreRequest = LoadMoreStrategy.Always
    override val useNewChapterEndpoint = true

    override val filterNonMangaItems = false
}
