package dev.sakayori.sakayomi.extension.ar.mangaspark

import dev.sakayori.sakayomi.multisrc.madara.Madara
import java.text.SimpleDateFormat
import java.util.Locale

class MangaSpark :
    Madara(
        "MangaSpark",
        "https://manga-spark.net",
        "ar",
        dateFormat = SimpleDateFormat("d MMMM، yyyy", Locale("ar")),
    ) {
    override val chapterUrlSuffix = ""
    override val useLoadMoreRequest = LoadMoreStrategy.Always
    override val useNewChapterEndpoint = false
}
