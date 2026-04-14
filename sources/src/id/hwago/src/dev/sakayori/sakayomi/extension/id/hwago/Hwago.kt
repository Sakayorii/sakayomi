package dev.sakayori.sakayomi.extension.id.hwago

import dev.sakayori.sakayomi.multisrc.madara.Madara
import java.text.SimpleDateFormat
import java.util.Locale

class Hwago :
    Madara(
        "Hwago",
        "https://01.hwago.xyz",
        "id",
        dateFormat = SimpleDateFormat("d MMMM yyyy", Locale("en")),
    ) {
    override val useNewChapterEndpoint = true

    override val mangaDetailsSelectorStatus = "div.summary-heading:contains(Status) + div.summary-content"

    override val mangaSubString = "komik"
}
