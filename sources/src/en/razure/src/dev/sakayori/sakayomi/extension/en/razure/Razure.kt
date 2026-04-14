package dev.sakayori.sakayomi.extension.en.razure

import dev.sakayori.sakayomi.multisrc.mangathemesia.MangaThemesia

class Razure :
    MangaThemesia(
        "Razure",
        "https://razure.org",
        "en",
        "/series",
    ) {
    override fun chapterListSelector() = "#chapterlist li:not([data-num*='🔒'])"

    override fun searchMangaSelector() = ".listupd .bs .bsx:not(:has(.novelabel))"
}
