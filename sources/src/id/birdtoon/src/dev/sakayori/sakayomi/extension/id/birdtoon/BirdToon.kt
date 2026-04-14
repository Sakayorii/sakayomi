package dev.sakayori.sakayomi.extension.id.birdtoon

import dev.sakayori.sakayomi.multisrc.madara.Madara

class BirdToon :
    Madara(
        "BirdToon",
        "https://birdtoon.shop",
        "id",
    ) {
    override val mangaSubString = "komik"

    override val useLoadMoreRequest = LoadMoreStrategy.Never
}
