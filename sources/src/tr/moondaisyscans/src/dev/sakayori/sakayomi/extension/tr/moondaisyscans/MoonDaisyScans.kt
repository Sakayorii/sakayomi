package dev.sakayori.sakayomi.extension.tr.moondaisyscans

import dev.sakayori.sakayomi.multisrc.mangathemesia.MangaThemesia
import dev.sakayori.sakayomi.network.interceptor.rateLimit
import java.text.SimpleDateFormat
import java.util.Locale

class MoonDaisyScans :
    MangaThemesia(
        "Moon Daisy Scans",
        "https://moondaisyscans.lol",
        "tr",
        dateFormat = SimpleDateFormat("MMMM d, yyy", Locale("tr")),
    ) {
    override val client = super.client.newBuilder()
        .rateLimit(3)
        .build()
}
