package dev.sakayori.sakayomi.extension.en.gakamangas

import dev.sakayori.sakayomi.multisrc.madara.Madara

class GakaMangas : Madara("GakaMangas", "https://gakamangas.com", "en") {
    override val useLoadMoreRequest = LoadMoreStrategy.Never
    override val useNewChapterEndpoint = true

    override val filterNonMangaItems = false
}
