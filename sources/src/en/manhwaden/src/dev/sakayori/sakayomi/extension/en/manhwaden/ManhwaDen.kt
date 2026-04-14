package dev.sakayori.sakayomi.extension.en.manhwaden

import dev.sakayori.sakayomi.multisrc.madara.Madara

class ManhwaDen :
    Madara(
        "ManhwaDen",
        "https://www.manhwaden.com",
        "en",
    ) {
    override val useLoadMoreRequest = LoadMoreStrategy.Never
    override val useNewChapterEndpoint = true

    override val filterNonMangaItems = false
}
