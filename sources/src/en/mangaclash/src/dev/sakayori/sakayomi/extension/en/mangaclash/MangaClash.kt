package dev.sakayori.sakayomi.extension.en.mangaclash

import dev.sakayori.sakayomi.multisrc.madara.Madara
import dev.sakayori.sakayomi.network.interceptor.rateLimit
import okhttp3.OkHttpClient
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.concurrent.TimeUnit

class MangaClash :
    Madara(
        "MangaClash",
        "https://mangaclash.com",
        "en",
        dateFormat = SimpleDateFormat("MM/dd/yy", Locale.US),
    ) {

    override val client: OkHttpClient = super.client.newBuilder()
        .rateLimit(1, 1, TimeUnit.SECONDS)
        .build()
}
