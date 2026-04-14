package dev.sakayori.sakayomi.extension.fr.softepsilonscan

import dev.sakayori.sakayomi.multisrc.madara.Madara
import dev.sakayori.sakayomi.network.interceptor.rateLimit
import okhttp3.OkHttpClient
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.concurrent.TimeUnit

class SoftEpsilonScan :
    Madara(
        "Soft Epsilon Scan",
        "https://epsilonsoft.to",
        "fr",
        SimpleDateFormat("dd/MM/yy", Locale.FRENCH),
    ) {
    override val client: OkHttpClient = super.client.newBuilder()
        .rateLimit(2, 1, TimeUnit.SECONDS)
        .build()

    override val useNewChapterEndpoint = true
}
