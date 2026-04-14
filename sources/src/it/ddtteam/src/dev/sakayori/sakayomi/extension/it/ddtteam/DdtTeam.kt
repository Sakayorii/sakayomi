package dev.sakayori.sakayomi.extension.it.ddtteam

import dev.sakayori.sakayomi.multisrc.pizzareader.PizzaReader
import dev.sakayori.sakayomi.network.interceptor.rateLimit
import kotlinx.serialization.json.Json

class DdtTeam :
    PizzaReader(
        "DDT Team",
        "https://ddt.hastateam.com",
        "it",
    ) {
    override val client = super.client.newBuilder()
        .addInterceptor { chain ->
            val url = chain.request().url.newBuilder()
                .scheme("https")
                .build()

            val request = chain.request().newBuilder()
                .url(url)
                .build()

            chain.proceed(request)
        }
        .rateLimit(1)
        .build()

    override val json = Json {
        ignoreUnknownKeys = true
        coerceInputValues = true
    }
}
