package dev.sakayori.sakayomi.extension.en.novel24h

import dev.sakayori.sakayomi.multisrc.madara.Madara

class Novel24h : Madara("24HNovel", "https://24hnovel.com", "en") {
    override val useLoadMoreRequest = LoadMoreStrategy.Never
    override val useNewChapterEndpoint = true
}
