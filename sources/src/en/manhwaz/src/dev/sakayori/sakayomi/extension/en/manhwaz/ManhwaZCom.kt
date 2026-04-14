package dev.sakayori.sakayomi.extension.en.manhwaz

import dev.sakayori.sakayomi.multisrc.manhwaz.ManhwaZ
import dev.sakayori.sakayomi.network.interceptor.rateLimit
import okhttp3.OkHttpClient

class ManhwaZCom :
    ManhwaZ(
        "ManhwaZ",
        "https://manhwaz.com",
        "en",
    ) {
    override val client: OkHttpClient = super.client.newBuilder()
        .rateLimit(2)
        .build()
}
