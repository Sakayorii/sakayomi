package dev.sakayori.sakayomi.extension.tr.mangakusu

import dev.sakayori.sakayomi.multisrc.mangathemesia.MangaThemesia
import dev.sakayori.sakayomi.network.interceptor.rateLimit
import java.text.SimpleDateFormat
import java.util.Locale

class MangaKusu :
    MangaThemesia(
        "Manga Kusu",
        "https://mangakusu.com",
        "tr",
        dateFormat = SimpleDateFormat("MMMM d, yyyy", Locale("tr")),
    ) {
    override val client = super.client.newBuilder()
        .rateLimit(3)
        .build()
}
