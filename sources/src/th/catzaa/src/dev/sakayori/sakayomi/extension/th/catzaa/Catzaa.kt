package dev.sakayori.sakayomi.extension.th.catzaa

import dev.sakayori.sakayomi.multisrc.madara.Madara
import java.text.SimpleDateFormat
import java.util.Locale

class Catzaa :
    Madara(
        "Catzaa",
        "https://catzaa.net",
        "th",
        dateFormat = SimpleDateFormat("d MMMM yyyy", Locale("th")),
    ) {
    override val useLoadMoreRequest = LoadMoreStrategy.Always
    override val useNewChapterEndpoint = false
}
