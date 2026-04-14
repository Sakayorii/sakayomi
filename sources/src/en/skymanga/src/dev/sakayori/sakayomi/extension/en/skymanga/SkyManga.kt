package dev.sakayori.sakayomi.extension.en.skymanga

import dev.sakayori.sakayomi.multisrc.mangathemesia.MangaThemesia
import dev.sakayori.sakayomi.network.interceptor.rateLimit
import java.text.SimpleDateFormat
import java.util.Locale

class SkyManga :
    MangaThemesia(
        "Sky Manga",
        "https://skymanga.work",
        "en",
        "/manga-list",
        SimpleDateFormat("dd-MM-yyyy", Locale.US),
    ) {
    override val client = super.client.newBuilder()
        .rateLimit(3)
        .build()
}
