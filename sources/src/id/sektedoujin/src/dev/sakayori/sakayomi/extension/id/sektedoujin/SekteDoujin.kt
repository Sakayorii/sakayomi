package dev.sakayori.sakayomi.extension.id.sektedoujin

import dev.sakayori.sakayomi.multisrc.mangathemesia.MangaThemesia
import dev.sakayori.sakayomi.network.interceptor.rateLimit
import okhttp3.OkHttpClient
import java.text.SimpleDateFormat
import java.util.Locale

class SekteDoujin : MangaThemesia("Sekte Doujin", "https://sektedoujin.cc", "id", dateFormat = SimpleDateFormat("MMMM dd, yyyy", Locale.forLanguageTag("id"))) {

    override val client: OkHttpClient = super.client.newBuilder()
        .rateLimit(4)
        .build()
}
