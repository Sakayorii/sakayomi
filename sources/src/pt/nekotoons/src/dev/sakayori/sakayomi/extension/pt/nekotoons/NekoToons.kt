package dev.sakayori.sakayomi.extension.pt.nekotoons

import dev.sakayori.sakayomi.multisrc.yuyu.YuYu
import dev.sakayori.sakayomi.network.interceptor.rateLimit

class NekoToons :
    YuYu(
        "Neko Toons",
        "https://nekotoons.site",
        "pt-BR",
    ) {

    override val client = super.client.newBuilder()
        .rateLimit(2)
        .build()
}
