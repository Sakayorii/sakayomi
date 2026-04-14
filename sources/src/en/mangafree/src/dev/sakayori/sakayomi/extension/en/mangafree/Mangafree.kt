package dev.sakayori.sakayomi.extension.en.mangafree

import dev.sakayori.sakayomi.multisrc.madara.Madara

class Mangafree : Madara("Mangafree", "https://mangafree.info", "en") {
    override val useLoadMoreRequest = LoadMoreStrategy.Always
    override val useNewChapterEndpoint = true
}
