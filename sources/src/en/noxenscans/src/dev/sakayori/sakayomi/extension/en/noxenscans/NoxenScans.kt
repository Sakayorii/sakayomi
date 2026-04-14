package dev.sakayori.sakayomi.extension.en.noxenscans

import dev.sakayori.sakayomi.multisrc.mangathemesia.MangaThemesia

class NoxenScans :
    MangaThemesia(
        "Noxen Scans",
        "https://noxenscan.com",
        "en",
    ) {
    override fun chapterListSelector(): String = "#chapterlist li:not(:has(svg))"
}
