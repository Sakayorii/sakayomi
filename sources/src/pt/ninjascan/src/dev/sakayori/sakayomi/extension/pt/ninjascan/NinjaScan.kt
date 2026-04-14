package dev.sakayori.sakayomi.extension.pt.ninjascan

import dev.sakayori.sakayomi.multisrc.madara.Madara
import dev.sakayori.sakayomi.network.interceptor.rateLimit
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.concurrent.TimeUnit

class NinjaScan :
    Madara(
        "Ninja Scan",
        "https://ninjacomics.xyz",
        "pt-BR",
        SimpleDateFormat("dd 'de' MMMMM 'de' yyyy", Locale("pt", "BR")),
    ) {
    override val client = super.client.newBuilder()
        .connectTimeout(5, TimeUnit.MINUTES)
        .readTimeout(5, TimeUnit.MINUTES)
        .rateLimit(2)
        .build()

    override val useLoadMoreRequest = LoadMoreStrategy.Never
    override val useNewChapterEndpoint = true
}
