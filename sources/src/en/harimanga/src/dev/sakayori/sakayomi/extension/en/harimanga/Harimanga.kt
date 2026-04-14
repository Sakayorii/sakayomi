package dev.sakayori.sakayomi.extension.en.harimanga

import dev.sakayori.sakayomi.multisrc.madara.Madara
import dev.sakayori.sakayomi.network.interceptor.rateLimit

class Harimanga :
    Madara(
        "Harimanga",
        "https://harimanga.me",
        "en",
    ) {
    override val client = super.client.newBuilder()
        .rateLimit(3)
        .build()

    override val useLoadMoreRequest = LoadMoreStrategy.Never
}
