package dev.sakayori.sakayomi.extension.ru.mangashi

import dev.sakayori.sakayomi.multisrc.madara.Madara
import java.text.SimpleDateFormat
import java.util.Locale

class MangaShi :
    Madara(
        "Manga-shi",
        "https://manga-shi.org",
        "ru",
        dateFormat = SimpleDateFormat("dd.MM.yyyy", Locale.ROOT),
    ) {
    override val useNewChapterEndpoint = true
    override val useLoadMoreRequest = LoadMoreStrategy.Always
}
