package dev.sakayori.sakayomi.extension.tr.ruyamanga

import dev.sakayori.sakayomi.multisrc.madara.Madara
import java.text.SimpleDateFormat
import java.util.Locale

class RuyaManga :
    Madara(
        "Rüya Manga",
        "https://www.ruyamanga2.com",
        "tr",
        SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH),
    ) {
    override val filterNonMangaItems = false
}
