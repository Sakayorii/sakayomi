package dev.sakayori.sakayomi.extension.en.manhwajoy

import dev.sakayori.sakayomi.multisrc.madara.Madara

class Manhwajoy : Madara("Manhwajoy", "https://manhwajoy.com", "en") {
    override val useLoadMoreRequest = LoadMoreStrategy.Never
    override val useNewChapterEndpoint = true
}
