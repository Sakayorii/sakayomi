package dev.sakayori.sakayomi.extension.es.lectormangalat

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

class LectorMangaLat :
    Madara(
        "LectorManga.lat",
        "https://lectormangaa.com",
        "es",
        dateFormat = SimpleDateFormat("MMMM dd, yyyy", Locale("es")),
    ),
    ConfigurableSource {

    override val client: OkHttpClient = super.client.newBuilder()
        .rateLimit(2, 1, TimeUnit.SECONDS)
        .build()

    override fun headersBuilder() = super.headersBuilder()
        .setRandomUserAgent()

    override fun getMangaUrl(manga: SManga) = "$baseUrl${manga.url}"

    override val mangaSubString = "biblioteca"

    override val useNewChapterEndpoint = true

    override val pageListParseSelector = "div.reading-content div.page-break > img"

    override fun setupPreferenceScreen(screen: PreferenceScreen) {
        screen.addRandomUAPreference()
    }
}
