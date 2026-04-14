package dev.sakayori.sakayomi.extension.pt.tankouhentai

import dev.sakayori.sakayomi.multisrc.madara.Madara
import dev.sakayori.sakayomi.network.interceptor.rateLimit
import okhttp3.OkHttpClient
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.concurrent.TimeUnit

class TankouHentai :
    Madara(
        "Tankou Hentai",
        "https://tankouhentai.com",
        "pt-BR",
        SimpleDateFormat("dd 'de' MMMMM 'de' YYYY", Locale("pt", "BR")),
    ) {

    override val client: OkHttpClient = super.client.newBuilder()
        .rateLimit(1, 2, TimeUnit.SECONDS)
        .build()

    override val useNewChapterEndpoint = true
}
