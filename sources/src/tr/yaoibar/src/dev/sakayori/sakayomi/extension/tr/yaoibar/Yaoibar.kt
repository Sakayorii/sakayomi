package dev.sakayori.sakayomi.extension.tr.yaoibar

import dev.sakayori.sakayomi.multisrc.madara.Madara
import java.text.SimpleDateFormat
import java.util.Locale

class Yaoibar :
    Madara(
        "Yaoibar",
        "https://yaoibar.lol",
        "tr",
        dateFormat = SimpleDateFormat("MMMM dd, yyyy", Locale("tr")),
    ) {
    override val useNewChapterEndpoint: Boolean = true
}
