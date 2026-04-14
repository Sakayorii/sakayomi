package dev.sakayori.sakayomi.extension.id.komikav

import dev.sakayori.sakayomi.multisrc.mangathemesia.MangaThemesia
import dev.sakayori.sakayomi.network.interceptor.rateLimit
import java.text.SimpleDateFormat
import java.util.Locale

class Apkomik :
    MangaThemesia(
        "APKOMIK",
        "https://apkomik.cc",
        "id",
        dateFormat = SimpleDateFormat("MMMM dd, yyyy", Locale.forLanguageTag("id")),
    ) {
    // Formerly "Komik AV (WP Manga Stream)"
    override val id = 7875815514004535629

    override val client = super.client.newBuilder()
        .rateLimit(4)
        .build()

    override val hasProjectPage = true
}
