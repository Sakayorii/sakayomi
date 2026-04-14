package dev.sakayori.sakayomi.extension.en.likemangain

import dev.sakayori.sakayomi.multisrc.madara.Madara
import java.text.SimpleDateFormat
import java.util.Locale

class MangaYY :
    Madara(
        "MangaYY",
        "https://mangayy.org",
        "en",
        dateFormat = SimpleDateFormat("dd MMMM, yyyy", Locale.US),
    ) {
    override val id = 828698548689586603
    override fun searchMangaSelector() = popularMangaSelector()
    override val useNewChapterEndpoint = true
}
