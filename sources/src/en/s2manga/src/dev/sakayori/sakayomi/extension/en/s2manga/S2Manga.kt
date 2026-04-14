package dev.sakayori.sakayomi.extension.en.s2manga

import dev.sakayori.sakayomi.multisrc.madara.Madara

class S2Manga : Madara("S2Manga", "https://s2manga.com", "en") {

    override val useLoadMoreRequest = LoadMoreStrategy.Always
    override val pageListParseSelector = "div.page-break img[src*=\"https\"]"
}
