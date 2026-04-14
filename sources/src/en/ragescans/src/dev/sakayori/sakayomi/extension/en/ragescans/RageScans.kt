package dev.sakayori.sakayomi.extension.en.ragescans

import dev.sakayori.sakayomi.multisrc.mangathemesia.MangaThemesia

class RageScans :
    MangaThemesia(
        "Rage Scans",
        "https://ragescans.com",
        "en",
    ) {
    override fun chapterListSelector() = "li:has(.chbox .eph-num):not(:has([data-bs-target='#lockedChapterModal']))"
}
