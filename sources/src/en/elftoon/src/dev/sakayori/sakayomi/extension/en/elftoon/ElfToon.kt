package dev.sakayori.sakayomi.extension.en.elftoon

import dev.sakayori.sakayomi.multisrc.mangathemesia.MangaThemesia

class ElfToon : MangaThemesia("Elf Toon", "https://elftoon.com", "en") {

    override fun chapterListSelector() = "#chapterlist li:not(:has(.gem-price-icon))"
}
