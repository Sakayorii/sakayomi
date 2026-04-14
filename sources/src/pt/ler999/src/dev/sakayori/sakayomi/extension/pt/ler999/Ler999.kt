package dev.sakayori.sakayomi.extension.pt.ler999

import dev.sakayori.sakayomi.multisrc.zeistmanga.ZeistManga
import dev.sakayori.sakayomi.network.interceptor.rateLimit
import java.util.concurrent.TimeUnit

class Ler999 : ZeistManga("Ler 999", "https://ler999.blogspot.com", "pt-BR") {
    override val client = super.client.newBuilder()
        .rateLimit(1, 2, TimeUnit.SECONDS)
        .build()
}
