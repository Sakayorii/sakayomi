package dev.sakayori.sakayomi.extension.en.topmanhuanet

import dev.sakayori.sakayomi.multisrc.madara.Madara

class TopManhuaNet :
    Madara(
        "TopManhua.net",
        "https://topmanhua.net",
        "en",
    ) {
    override val useLoadMoreRequest = LoadMoreStrategy.Never
    override val useNewChapterEndpoint = true
}
