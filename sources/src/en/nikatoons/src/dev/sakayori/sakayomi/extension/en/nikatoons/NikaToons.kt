package dev.sakayori.sakayomi.extension.en.nikatoons

import dev.sakayori.sakayomi.multisrc.mangathemesia.MangaThemesia

class NikaToons :
    MangaThemesia(
        "Nika Toons",
        "https://nikatoons.com",
        "en",
    ) {
    override fun chapterListSelector() = "#chapterlist li.chapter-item:not(.premium)"
}
