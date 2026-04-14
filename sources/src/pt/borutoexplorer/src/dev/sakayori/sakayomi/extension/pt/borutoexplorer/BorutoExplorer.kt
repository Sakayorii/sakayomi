package dev.sakayori.sakayomi.extension.pt.borutoexplorer

import dev.sakayori.sakayomi.multisrc.madara.Madara
import dev.sakayori.sakayomi.network.interceptor.rateLimit
import okhttp3.OkHttpClient
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.concurrent.TimeUnit

class BorutoExplorer :
    Madara(
        "Boruto Explorer",
        "https://leitor.borutoexplorer.com.br",
        "pt-BR",
        SimpleDateFormat("dd 'de' MMMMM 'de' yyyy", Locale("pt", "BR")),
    ) {

    override val client: OkHttpClient = super.client.newBuilder()
        .rateLimit(1, 2, TimeUnit.SECONDS)
        .build()
}
