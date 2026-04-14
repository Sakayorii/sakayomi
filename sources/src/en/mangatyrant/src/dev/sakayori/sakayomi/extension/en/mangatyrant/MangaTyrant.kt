package dev.sakayori.sakayomi.extension.en.mangatyrant

import dev.sakayori.sakayomi.multisrc.madara.Madara

class MangaTyrant : Madara("MangaTyrant", "https://mangatyrant.com", "en") {
    override val useNewChapterEndpoint = true
    override val filterNonMangaItems = false
}
