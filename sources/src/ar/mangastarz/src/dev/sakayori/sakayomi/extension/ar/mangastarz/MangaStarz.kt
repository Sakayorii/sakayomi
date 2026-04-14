package dev.sakayori.sakayomi.extension.ar.mangastarz

import dev.sakayori.sakayomi.multisrc.madara.Madara
import java.text.SimpleDateFormat
import java.util.Locale

class MangaStarz :
    Madara(
        "Manga Starz",
        "https://manga-starz.net",
        "ar",
        dateFormat = SimpleDateFormat("d MMMM، yyyy", Locale("ar")),
    ) {
    override val chapterUrlSuffix = ""
    override val useLoadMoreRequest = LoadMoreStrategy.Never
    override val useNewChapterEndpoint = false
}
