package dev.sakayori.sakayomi.extension.id.kumopoi

import dev.sakayori.sakayomi.multisrc.mangathemesia.MangaThemesia
import java.text.SimpleDateFormat
import java.util.Locale

class KumoPoi :
    MangaThemesia(
        "KumoPoi",
        "https://kumopoi.org",
        "id",
        "/manga",
        dateFormat = SimpleDateFormat("MMMM dd, yyyy", Locale.forLanguageTag("id")),
    ) {

    override val hasProjectPage = true
}
