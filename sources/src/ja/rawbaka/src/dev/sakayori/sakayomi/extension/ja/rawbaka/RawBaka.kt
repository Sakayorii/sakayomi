package dev.sakayori.sakayomi.extension.ja.rawbaka

import dev.sakayori.sakayomi.multisrc.madara.Madara

class RawBaka : Madara("RawBaka", "https://rawbaka.com", "ja") {
    override val mangaEntrySelector = ".text"
    override val useLoadMoreRequest = LoadMoreStrategy.Never
    override val useNewChapterEndpoint = true
}
