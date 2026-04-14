package dev.sakayori.sakayomi.extension.tr.mangasehri

import dev.sakayori.sakayomi.multisrc.madara.Madara
import java.text.SimpleDateFormat
import java.util.Locale

class MangaSehri :
    Madara(
        "Manga Şehri",
        "https://manga-sehri.com",
        "tr",
        SimpleDateFormat("dd/MM/yyy", Locale("tr")),
    ) {
    override val useNewChapterEndpoint = false
}
