package dev.sakayori.sakayomi.extension.en.manhuafast

import dev.sakayori.sakayomi.multisrc.madara.Madara
import dev.sakayori.sakayomi.network.interceptor.rateLimit
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit

class ManhuaFast : Madara("ManhuaFast", "https://manhuafast.com", "en") {

    override val client: OkHttpClient = super.client.newBuilder()
        .rateLimit(20, 4, TimeUnit.SECONDS)
        .build()

    // The website does not flag the content.
    override val filterNonMangaItems = false
}
