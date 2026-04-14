package dev.sakayori.sakayomi.extension.es.colorcitoscan

import dev.sakayori.sakayomi.multisrc.spicytheme.SpicyTheme
import dev.sakayori.sakayomi.network.interceptor.rateLimit

class ColorcitoScan :
    SpicyTheme(
        name = "Colorcito Scan",
        baseUrl = "https://colorcitoscan.com",
        apiBaseUrl = "https://api.colorcitoscan.com",
        lang = "es",
    ) {
    override val client = super.client.newBuilder()
        .rateLimit(2)
        .build()
}
