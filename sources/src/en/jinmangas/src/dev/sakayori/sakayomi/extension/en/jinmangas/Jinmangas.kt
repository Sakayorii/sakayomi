package dev.sakayori.sakayomi.extension.en.jinmangas

import dev.sakayori.sakayomi.multisrc.madara.Madara

class Jinmangas : Madara("Jinmangas", "https://jinmangas.com", "en") {
    override val useLoadMoreRequest = LoadMoreStrategy.Always
    override val useNewChapterEndpoint = true
}
