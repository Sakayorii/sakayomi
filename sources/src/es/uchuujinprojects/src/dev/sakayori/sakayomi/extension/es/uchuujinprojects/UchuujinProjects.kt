package dev.sakayori.sakayomi.extension.es.uchuujinprojects

import dev.sakayori.sakayomi.multisrc.mangathemesia.MangaThemesia
import dev.sakayori.sakayomi.network.interceptor.rateLimitHost
import okhttp3.HttpUrl.Companion.toHttpUrl
import java.text.SimpleDateFormat
import java.util.Locale

class UchuujinProjects :
    MangaThemesia(
        "Uchuujin Projects",
        "https://uchuujinmangas.com",
        "es",
        dateFormat = SimpleDateFormat("dd 'de' MMMM 'de' yyyy", Locale("es")),
    ) {
    override val client = super.client.newBuilder()
        .rateLimitHost(baseUrl.toHttpUrl(), 3, 1)
        .build()

    override val hasProjectPage = true
}
