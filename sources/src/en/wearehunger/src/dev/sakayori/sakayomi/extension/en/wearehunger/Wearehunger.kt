package dev.sakayori.sakayomi.extension.en.wearehunger

import dev.sakayori.sakayomi.multisrc.madara.Madara

class Wearehunger :
    Madara(
        "Wearehunger",
        "https://www.wearehunger.site",
        "en",
    ) {
    override val useLoadMoreRequest = LoadMoreStrategy.Never
    override val useNewChapterEndpoint = true
}
