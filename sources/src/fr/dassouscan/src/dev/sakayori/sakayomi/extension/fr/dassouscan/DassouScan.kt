package dev.sakayori.sakayomi.extension.fr.dassouscan

import dev.sakayori.sakayomi.multisrc.madara.Madara
import java.text.SimpleDateFormat
import java.util.Locale

class DassouScan :
    Madara(
        "Dassou Scan",
        "https://dassouscan.com",
        "fr",
        dateFormat = SimpleDateFormat("MMMM d, yyyy", Locale("fr")),
    ) {
    override val useLoadMoreRequest = LoadMoreStrategy.Never
    override val useNewChapterEndpoint = true
}
