package dev.sakayori.sakayomi.extension.ko.rawdex

import dev.sakayori.sakayomi.multisrc.madara.Madara
import java.text.SimpleDateFormat
import java.util.Locale

class RawDEX :
    Madara(
        "RawDEX",
        "https://rawdex.net",
        "ko",
        SimpleDateFormat("dd.MM.yyyy", Locale.ROOT),
    ) {

    override val mangaDetailsSelectorStatus = "div.summary-heading:has(h5:contains(Status)) + div"
    override val chapterUrlSuffix = ""
    override fun searchMangaSelector() = "div.page-item-detail.manga"
}
