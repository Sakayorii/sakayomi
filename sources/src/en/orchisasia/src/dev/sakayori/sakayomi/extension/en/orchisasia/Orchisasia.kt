package dev.sakayori.sakayomi.extension.en.orchisasia

import dev.sakayori.sakayomi.multisrc.madara.Madara

class Orchisasia : Madara("Orchisasia", "https://www.orchisasia.org", "en") {
    override val mangaSubString = "comic"
    override val useLoadMoreRequest = LoadMoreStrategy.Never
    override val useNewChapterEndpoint = false
}
