package dev.sakayori.sakayomi.extension.ca.fansubscathentai

import dev.sakayori.sakayomi.multisrc.fansubscat.FansubsCat

class HentaiCat :
    FansubsCat(
        "Hentai.cat",
        "https://manga.hentai.cat",
        "ca",
        "https://api.hentai.cat",
        isHentaiSite = true,
    ) {
    override val id: Long = 7575385310756416449
}
