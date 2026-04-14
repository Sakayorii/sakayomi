package dev.sakayori.sakayomi.extension.tr.caprazmanga

import dev.sakayori.sakayomi.multisrc.madara.Madara

class CaprazManga : Madara("ÇaprazManga", "https://caprazmanga.com", "tr") {
    override val useLoadMoreRequest = LoadMoreStrategy.Never
    override val useNewChapterEndpoint = true
}
