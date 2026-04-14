package dev.sakayori.sakayomi.extension.tr.adonisfansub

import dev.sakayori.sakayomi.multisrc.madara.Madara
import dev.sakayori.sakayomi.network.GET
import okhttp3.Request

class AdonisFansub : Madara("Adonis Fansub", "https://manga.adonisfansub.com", "tr") {
    override fun popularMangaRequest(page: Int): Request = GET("$baseUrl/manga/page/$page/?m_orderby=views", headers)
    override fun latestUpdatesRequest(page: Int): Request = GET("$baseUrl/manga/page/$page/?m_orderby=latest", headers)
}
