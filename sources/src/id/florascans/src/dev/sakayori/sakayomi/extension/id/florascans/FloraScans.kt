package dev.sakayori.sakayomi.extension.id.florascans

import dev.sakayori.sakayomi.multisrc.mangathemesia.MangaThemesia
import java.text.SimpleDateFormat
import java.util.Locale

class FloraScans :
    MangaThemesia(
        "FloraScans",
        "https://florascans.net",
        "id",
        "/manga",
        dateFormat = SimpleDateFormat("MMMM dd, yyyy", Locale.forLanguageTag("id")),
    ) {

    override val hasProjectPage = true
}
