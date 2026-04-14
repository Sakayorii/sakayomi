package dev.sakayori.sakayomi.extension.es.mangalector

import dev.sakayori.sakayomi.multisrc.madara.Madara
import dev.sakayori.sakayomi.network.GET
import dev.sakayori.sakayomi.network.asObservableSuccess
import dev.sakayori.sakayomi.source.model.FilterList
import dev.sakayori.sakayomi.source.model.MangasPage
import dev.sakayori.sakayomi.source.model.Page
import dev.sakayori.sakayomi.source.model.SChapter
import dev.sakayori.sakayomi.util.asJsoup
import okhttp3.HttpUrl.Companion.toHttpUrlOrNull
import okhttp3.Request
import okhttp3.Response
import org.jsoup.nodes.Document
import rx.Observable

class Mangalector :
    Madara(
        "MangaLector",
        "https://mangalector.com",
        "es",
    ) {

    override val supportsLatest = true

    // ================================= Popular =================================
    override fun popularMangaRequest(page: Int): Request = GET("$baseUrl/popular-manga?page=$page", headers)

    // ========================= Latest =========================
    override fun latestUpdatesRequest(page: Int): Request = GET("$baseUrl/latest-manga?page=$page", headers)

    override fun latestUpdatesParse(response: Response): MangasPage = popularMangaParse(response)

    // ========================= Search =========================
    override fun fetchSearchManga(page: Int, query: String, filters: FilterList): Observable<MangasPage> {
        val trimmedQuery = query.trim()
        if (trimmedQuery.startsWith("https://")) {
            val url = trimmedQuery.toHttpUrlOrNull()
            if (url != null && url.host == DOMAIN) {
                val fullUrl = if (url.pathSegments.firstOrNull() != "manga") {
                    val slug = url.pathSegments.lastOrNull()?.substringBefore("-capitulo-")
                    if (!slug.isNullOrBlank()) {
                        "$baseUrl/manga/$slug"
                    } else {
                        trimmedQuery
                    }
                } else {
                    trimmedQuery
                }

                return client.newCall(GET(fullUrl, headers))
                    .asObservableSuccess()
                    .map { response ->
                        val manga = mangaDetailsParse(response).apply {
                            setUrlWithoutDomain(fullUrl)
                        }

                        if (manga.title.isEmpty()) {
                            MangasPage(emptyList(), false)
                        } else {
                            MangasPage(listOf(manga), false)
                        }
                    }
            }
        }
        return super.fetchSearchManga(page, query, filters)
    }

    override fun searchMangaRequest(page: Int, query: String, filters: FilterList): Request {
        val url = "$baseUrl/search".toHttpUrlOrNull()!!.newBuilder()
            .addQueryParameter("s", query)
            .addQueryParameter("page", page.toString())
            .addQueryParameter("post_type", "wp-manga")
            .build()

        return GET(url.toString(), headers)
    }

    // ========================= Chapters =========================
    override fun chapterListParse(response: Response): List<SChapter> {
        val document = response.asJsoup()

        val mangaId = document.select("#manga-chapters-holder").attr("data-id")
        if (mangaId.isNullOrBlank()) {
            throw Exception("No se pudo encontrar el ID del manga.")
        }

        val xhrRequest = GET("$baseUrl/ajax-list-chapter?mangaID=$mangaId", headers)
        val xhrResponse = client.newCall(xhrRequest).execute()
        val xhrDocument = xhrResponse.asJsoup()
        val chapterLinks = xhrDocument.select("div.listing-chapters_wrap li a")

        return chapterLinks.mapNotNull { a ->
            val href = a.attr("abs:href")
            val chapterName = a.text().trim()

            SChapter.create().apply {
                name = chapterName
                setUrlWithoutDomain(href)
            }
        }.distinctBy { it.url }
    }

    // ========================= Pages =========================
    override fun pageListParse(document: Document): List<Page> {
        val stringArray = document.select("p#arraydata").text().split(",").toTypedArray()
        return stringArray.mapIndexed { index, url ->
            Page(
                index,
                document.location(),
                url,
            )
        }
    }

    companion object {
        private const val DOMAIN = "mangalector.com"
    }
}
