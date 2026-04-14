package dev.sakayori.sakayomi.extension.en.manga18free

import dev.sakayori.sakayomi.multisrc.madara.Madara

class Manga18Free :
    Madara(
        "Manga18Free",
        "https://manga18free.com",
        "en",
    ) {
    override fun searchMangaNextPageSelector() = "a.nextpostslink"
}
