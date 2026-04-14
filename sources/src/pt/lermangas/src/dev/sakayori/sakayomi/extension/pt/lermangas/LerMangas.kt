package dev.sakayori.sakayomi.extension.pt.lermangas

import dev.sakayori.sakayomi.multisrc.madara.Madara
import dev.sakayori.sakayomi.network.interceptor.rateLimit
import okhttp3.OkHttpClient
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.concurrent.TimeUnit

class LerMangas :
    Madara(
        "Ler Mangas",
        "https://lermangas.me",
        "pt-BR",
        SimpleDateFormat("dd 'de' MMMMM 'de' yyyy", Locale("pt", "BR")),
    ) {
    override val client: OkHttpClient = super.client.newBuilder()
        .rateLimit(1, 2, TimeUnit.SECONDS)
        .build()
}
