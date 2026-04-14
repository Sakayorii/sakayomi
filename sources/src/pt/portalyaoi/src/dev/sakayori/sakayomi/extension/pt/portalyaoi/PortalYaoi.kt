package dev.sakayori.sakayomi.extension.pt.portalyaoi

import dev.sakayori.sakayomi.multisrc.madara.Madara
import dev.sakayori.sakayomi.network.interceptor.rateLimit
import okhttp3.OkHttpClient
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.concurrent.TimeUnit

class PortalYaoi :
    Madara(
        "Portal Yaoi",
        "https://lerboyslove.com",
        "pt-BR",
        SimpleDateFormat("dd/MM/yyyy", Locale("pt", "BR")),
    ) {

    override val client: OkHttpClient = super.client.newBuilder()
        .rateLimit(1, 2, TimeUnit.SECONDS)
        .build()
}
