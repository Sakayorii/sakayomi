package dev.sakayori.sakayomi.extension.en.evilflowers

import dev.sakayori.sakayomi.multisrc.madara.Madara

class EvilFlowers :
    Madara(
        "Evil Flowers",
        "https://evilflowers.com",
        "en",
    ) {
    override val versionId = 2

    override val mangaSubString = "project"

    override val useLoadMoreRequest = LoadMoreStrategy.Never

    override val useNewChapterEndpoint = true
}
