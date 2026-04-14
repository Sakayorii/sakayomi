package dev.sakayori.sakayomi.extension.ar.goldenmanga

import dev.sakayori.sakayomi.multisrc.madara.Madara

class GoldenManga : Madara("Golden Manga", "https://goldenmanga.net", "ar") {
    override val useLoadMoreRequest = LoadMoreStrategy.Never
    override val useNewChapterEndpoint = true
}
