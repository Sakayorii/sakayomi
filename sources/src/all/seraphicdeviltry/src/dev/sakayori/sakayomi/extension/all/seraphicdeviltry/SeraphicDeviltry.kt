package dev.sakayori.sakayomi.extension.all.seraphicdeviltry

import dev.sakayori.sakayomi.multisrc.madara.Madara
import dev.sakayori.sakayomi.network.interceptor.rateLimit
import okhttp3.OkHttpClient
import java.text.SimpleDateFormat
import java.util.Locale

class SeraphicDeviltry(
    lang: String,
    baseUrl: String,
) : Madara(
    "SeraphicDeviltry",
    baseUrl,
    lang,
    dateFormat = SimpleDateFormat("MM/dd/yyyy", Locale("US")),
) {
    override val client: OkHttpClient = super.client.newBuilder()
        .rateLimit(3, 1)
        .build()
}
