package dev.sakayori.sakayomi.extension.en.hm2d

import dev.sakayori.sakayomi.multisrc.madara.Madara

class HM2D :
    Madara(
        "HM2D",
        "https://doujindistrict.com",
        "en",
    ) {
    override fun searchMangaNextPageSelector() = "div[role=navigation] span.current + a.page"
}
