package dev.sakayori.sakayomi.extension.tr.lilyumfansub

import dev.sakayori.sakayomi.multisrc.madara.Madara
import java.text.SimpleDateFormat
import java.util.Locale

class LilyumFansub :
    Madara(
        "LilyumFansub",
        "https://lilyumfansub.com.tr",
        "tr",
        SimpleDateFormat("MMMM dd yyyy", Locale("tr")),
    ) {
    override val useNewChapterEndpoint = true
}
