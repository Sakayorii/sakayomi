package dev.sakayori.sakayomi.extension.en.mangadrama

import dev.sakayori.sakayomi.multisrc.madara.Madara
import dev.sakayori.sakayomi.multisrc.madara.Madara.LoadMoreStrategy

class MangaDrama : Madara("Manga Drama", "https://mangadrama.com", "en") {

    override val useLoadMoreRequest = LoadMoreStrategy.Never
    override val useNewChapterEndpoint = true
    override fun chapterListSelector() = "li.wp-manga-chapter.free-chap"

    override fun searchMangaSelector() = popularMangaSelector()
}
