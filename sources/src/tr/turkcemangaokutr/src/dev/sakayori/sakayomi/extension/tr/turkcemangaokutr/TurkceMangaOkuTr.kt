package dev.sakayori.sakayomi.extension.tr.turkcemangaokutr

import dev.sakayori.sakayomi.multisrc.madara.Madara

class TurkceMangaOkuTr :
    Madara(
        "Türkçe Manga Oku TR",
        "https://turkcemangaoku.com.tr",
        "tr",
    ) {
    override val useLoadMoreRequest = LoadMoreStrategy.Never
    override val useNewChapterEndpoint = true
}
