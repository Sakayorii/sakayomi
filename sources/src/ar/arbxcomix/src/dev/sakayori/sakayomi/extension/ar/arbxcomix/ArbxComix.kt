package dev.sakayori.sakayomi.extension.ar.arbxcomix

import dev.sakayori.sakayomi.multisrc.madara.Madara

class ArbxComix : Madara("ArbxComix", "https://arbxcomix.com", "ar") {
    override fun searchMangaSelector() = popularMangaSelector()
    override val useLoadMoreRequest = LoadMoreStrategy.Never
    override val useNewChapterEndpoint = true
}
