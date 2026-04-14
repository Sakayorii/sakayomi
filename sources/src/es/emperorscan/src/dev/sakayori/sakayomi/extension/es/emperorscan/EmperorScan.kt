package dev.sakayori.sakayomi.extension.es.emperorscan

import androidx.preference.PreferenceScreen
import dev.sakayori.sakayomi.multisrc.madara.Madara
import dev.sakayori.sakayomi.network.interceptor.rateLimitHost
import dev.sakayori.sakayomi.source.ConfigurableSource
import dev.sakayori.sakayomi.source.model.SManga
import Sakayorii.lib.randomua.addRandomUAPreference
import Sakayorii.lib.randomua.setRandomUserAgent
import okhttp3.HttpUrl.Companion.toHttpUrl
import java.text.SimpleDateFormat
import java.util.Locale

class EmperorScan :
    Madara(
        "Emperor Scan",
        "https://imperiomanhua.com",
        "es",
        SimpleDateFormat("MMMM dd, yyyy", Locale("es")),
    ),
    ConfigurableSource {

    override val useLoadMoreRequest = LoadMoreStrategy.Never
    override val useNewChapterEndpoint = true

    override val client = super.client.newBuilder()
        .rateLimitHost(baseUrl.toHttpUrl(), 2)
        .build()

    override fun headersBuilder() = super.headersBuilder()
        .setRandomUserAgent()

    override fun getMangaUrl(manga: SManga) = "$baseUrl${manga.url}"

    override val mangaDetailsSelectorDescription = "div.summary__content p:not(p:has(a))"

    override fun setupPreferenceScreen(screen: PreferenceScreen) {
        screen.addRandomUAPreference()
    }
}
