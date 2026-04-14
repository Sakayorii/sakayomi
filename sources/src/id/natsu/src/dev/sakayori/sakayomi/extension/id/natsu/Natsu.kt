package dev.sakayori.sakayomi.extension.id.natsu

import dev.sakayori.sakayomi.multisrc.natsuid.NatsuId
import dev.sakayori.sakayomi.network.interceptor.rateLimit
import okhttp3.OkHttpClient

class Natsu :
    NatsuId(
        "Natsu",
        "id",
        "https://natsu.tv",
    ) {
    override fun OkHttpClient.Builder.customizeClient() = rateLimit(4)
}
