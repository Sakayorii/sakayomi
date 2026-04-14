package dev.sakayori.sakayomi.extension.es.mangashiina

import dev.sakayori.sakayomi.multisrc.mangathemesia.MangaThemesia
import java.text.SimpleDateFormat
import java.util.Locale

class MangaMukai :
    MangaThemesia(
        "Manga Mukai",
        "https://mangamukai.com",
        "es",
        dateFormat = SimpleDateFormat("MMMM dd, yyyy", Locale("es")),
    ) {
    override val id: Long = 711368877221654433
}
