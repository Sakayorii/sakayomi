package dev.sakayori.sakayomi.extension.pt.atemporal

import dev.sakayori.sakayomi.multisrc.mangathemesia.MangaThemesia
import dev.sakayori.sakayomi.network.interceptor.rateLimit
import java.text.SimpleDateFormat
import java.util.Locale

class Atemporal :
    MangaThemesia(
        "Atemporal",
        "https://atemporal.cloud",
        "pt-BR",
        dateFormat = SimpleDateFormat("MMM dd, yyyy", Locale("pt", "BR")),
    ) {
    override val client = super.client.newBuilder()
        .rateLimit(2)
        .build()
}
