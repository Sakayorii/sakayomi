package dev.sakayori.sakayomi.extension.en.manhwacomics

import dev.sakayori.sakayomi.multisrc.madara.Madara
import java.text.SimpleDateFormat
import java.util.Locale

class ManhwaComics :
    Madara(
        "Manhwa Comics",
        "https://manhwacomics.com",
        "en",
        dateFormat = SimpleDateFormat("d MMM yyyy", Locale.US),
    ) {
    override val mangaSubString = "manhwa"
    override val useLoadMoreRequest = LoadMoreStrategy.Never
    override val useNewChapterEndpoint = true
}
