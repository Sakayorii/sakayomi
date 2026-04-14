package dev.sakayori.sakayomi.extension.pt.remangas

import androidx.preference.PreferenceScreen
import dev.sakayori.sakayomi.multisrc.madara.Madara
import dev.sakayori.sakayomi.network.interceptor.rateLimit
import dev.sakayori.sakayomi.source.ConfigurableSource
import dev.sakayori.sakayomi.source.model.SManga
import Sakayorii.lib.randomua.addRandomUAPreference
import Sakayorii.lib.randomua.setRandomUserAgent
import okhttp3.OkHttpClient
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.concurrent.TimeUnit

class Remangas :
    Madara(
        "Remangas",
        "https://remangas.net",
        "pt-BR",
        SimpleDateFormat("dd/MM/yyyy", Locale("pt", "BR")),
    ),
    ConfigurableSource {

    override val useLoadMoreRequest = LoadMoreStrategy.Always

    override fun headersBuilder() = super.headersBuilder()
        .setRandomUserAgent()

    override val client: OkHttpClient = super.client.newBuilder()
        .rateLimit(2)
        .readTimeout(1, TimeUnit.MINUTES)
        .connectTimeout(1, TimeUnit.MINUTES)
        .build()

    override fun setupPreferenceScreen(screen: PreferenceScreen) {
        screen.addRandomUAPreference()
    }

    override fun getMangaUrl(manga: SManga) = "$baseUrl${manga.url}"
}
