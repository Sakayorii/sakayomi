package dev.sakayori.sakayomi.extension.en.manhwatoon

import dev.sakayori.sakayomi.multisrc.madara.Madara

class ManhwaToon :
    Madara(
        "Manhwa Toon",
        "https://www.manhwatoon.me",
        "en",
    ) {
    override val useNewChapterEndpoint = true

    override val useLoadMoreRequest = LoadMoreStrategy.Always
}
