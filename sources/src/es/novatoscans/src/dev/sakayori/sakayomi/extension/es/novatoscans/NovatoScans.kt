package dev.sakayori.sakayomi.extension.es.novatoscans

import dev.sakayori.sakayomi.multisrc.zeistmanga.ZeistManga
import dev.sakayori.sakayomi.network.interceptor.rateLimit

class NovatoScans :
    ZeistManga(
        "Novato Scans",
        "https://www.novatoscans.top",
        "es",
    ) {
    override val client = super.client.newBuilder()
        .rateLimit(3)
        .build()

    override val pageListSelector = "div.check-box"
}
