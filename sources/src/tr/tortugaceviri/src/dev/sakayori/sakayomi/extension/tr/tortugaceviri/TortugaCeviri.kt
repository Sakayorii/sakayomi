package dev.sakayori.sakayomi.extension.tr.tortugaceviri

import dev.sakayori.sakayomi.multisrc.madara.Madara
import java.text.SimpleDateFormat
import java.util.Locale

class TortugaCeviri :
    Madara(
        "Tortuga Ceviri",
        "https://tortugaceviri.com",
        "tr",
        SimpleDateFormat("MMM d, yyy", Locale("tr")),
    ) {
    override val versionId = 2

    override val useNewChapterEndpoint = true
}
