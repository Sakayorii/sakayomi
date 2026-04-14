package dev.sakayori.sakayomi.extension.id.pornhwa18

import dev.sakayori.sakayomi.multisrc.madara.Madara
import dev.sakayori.sakayomi.network.GET
import okhttp3.Request

class Pornhwa18 : Madara("Pornhwa18", "https://pornhwa18.com", "id") {
    override val filterNonMangaItems = false
    override fun popularMangaRequest(page: Int): Request = GET("$baseUrl/series/page/$page/?m_orderby=views", headers)
    override fun latestUpdatesRequest(page: Int): Request = GET("$baseUrl/series/page/$page/?m_orderby=latest", headers)
}
