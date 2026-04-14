package dev.sakayori.sakayomi.extension.en.manhuazonghe

import dev.sakayori.sakayomi.multisrc.madara.Madara

class ManhuaZonghe : Madara("Manhua Zonghe", "https://www.manhuazonghe.com", "en") {
    override val useNewChapterEndpoint = false
    override val filterNonMangaItems = false
    override val mangaSubString = "manhua"
}
