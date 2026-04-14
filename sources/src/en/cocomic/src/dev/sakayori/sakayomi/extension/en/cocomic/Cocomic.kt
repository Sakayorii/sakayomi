package dev.sakayori.sakayomi.extension.en.cocomic

import dev.sakayori.sakayomi.multisrc.madara.Madara
import dev.sakayori.sakayomi.network.interceptor.rateLimit
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit

class Cocomic : Madara("Cocomic", "https://cocomic.co", "en") {

    override val client: OkHttpClient = super.client.newBuilder()
        .rateLimit(1, 2, TimeUnit.SECONDS)
        .build()

    override val useNewChapterEndpoint = true

    override fun chapterListSelector() = "li.wp-manga-chapter:not(.premium)"
}
