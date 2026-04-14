package dev.sakayori.sakayomi.extension.es.spicyscan

import dev.sakayori.sakayomi.multisrc.spicytheme.SpicyTheme
import dev.sakayori.sakayomi.network.interceptor.rateLimit

class SpicyScan :
    SpicyTheme(
        name = "Spicy Scan",
        baseUrl = "https://spicyseries.com",
        apiBaseUrl = "https://back.spicyseries.com",
        lang = "es",
    ) {
    override val client = super.client.newBuilder()
        .rateLimit(2)
        .build()
}
