package dev.sakayori.sakayomi.extension.all.mangacrazy

import dev.sakayori.sakayomi.multisrc.madara.Madara

class MangaCrazy : Madara("MangaCrazy", "https://mangacrazy.net", "all") {
    override val useNewChapterEndpoint = true
}
