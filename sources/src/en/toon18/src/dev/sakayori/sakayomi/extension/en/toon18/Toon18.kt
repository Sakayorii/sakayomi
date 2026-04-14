package dev.sakayori.sakayomi.extension.en.toon18

import dev.sakayori.sakayomi.multisrc.madara.Madara

class Toon18 : Madara("Toon18", "https://toon18.to", "en") {
    override val useLoadMoreRequest = LoadMoreStrategy.Never
    override val useNewChapterEndpoint = false
}
