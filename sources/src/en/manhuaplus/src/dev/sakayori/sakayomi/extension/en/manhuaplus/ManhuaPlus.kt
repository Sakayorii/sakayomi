package dev.sakayori.sakayomi.extension.en.manhuaplus

import dev.sakayori.sakayomi.multisrc.madara.Madara

class ManhuaPlus : Madara("Manhua Plus", "https://manhuaplus.com", "en") {

    // The website does not flag the content.
    override val filterNonMangaItems = false
    override val useLoadMoreRequest = LoadMoreStrategy.Never
    override val pageListParseSelector = ".read-container img"
}
