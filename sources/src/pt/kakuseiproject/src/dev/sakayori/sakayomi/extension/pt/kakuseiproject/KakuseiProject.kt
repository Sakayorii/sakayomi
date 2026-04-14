package dev.sakayori.sakayomi.extension.pt.kakuseiproject

import dev.sakayori.sakayomi.multisrc.madara.Madara
import dev.sakayori.sakayomi.network.interceptor.rateLimit
import okhttp3.OkHttpClient
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.concurrent.TimeUnit

class KakuseiProject :
    Madara(
        "Kakusei Project",
        "https://kakuseiproject.com",
        "pt-BR",
        SimpleDateFormat("MMMMM dd, yyyy", Locale("pt", "BR")),
    ) {

    override val client: OkHttpClient = super.client.newBuilder()
        .rateLimit(1, 2, TimeUnit.SECONDS)
        .build()

    override val useNewChapterEndpoint = true

    override val useLoadMoreRequest = LoadMoreStrategy.Always
}
