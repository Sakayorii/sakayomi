package dev.sakayori.sakayomi.extension.ja.comicnettai

import dev.sakayori.sakayomi.network.GET
import dev.sakayori.sakayomi.source.model.FilterList
import dev.sakayori.sakayomi.source.model.MangasPage
import dev.sakayori.sakayomi.source.model.Page
import dev.sakayori.sakayomi.source.model.SChapter
import dev.sakayori.sakayomi.source.model.SManga
import dev.sakayori.sakayomi.source.online.HttpSource
import dev.sakayori.sakayomi.util.asJsoup
import Sakayorii.lib.publus.PublusInterceptor
import Sakayorii.lib.publus.fetchPages
import Sakayorii.utils.parseAs
import Sakayorii.utils.tryParse
import kotlinx.serialization.Serializable
import okhttp3.HttpUrl.Companion.toHttpUrl
import okhttp3.Request
import okhttp3.Response
import java.text.SimpleDateFormat
import java.util.Locale

class ComicNettai : HttpSource() {
    override val name = "Comic Nettai"
    override val baseUrl = "https://www.comicnettai.com"
    override val lang = "ja"
    override val supportsLatest = false

    private val dateFormat = SimpleDateFormat("yyyy.MM.dd", Locale.ROOT)

    override val client = network.cloudflareClient.newBuilder()
        .addInterceptor(PublusInterceptor())
        .build()

    override fun headersBuilder() = super.headersBuilder()
        .add("Referer", "$baseUrl/")

    override fun popularMangaRequest(page: Int): Request {
        val url = "$baseUrl/series".toHttpUrl().newBuilder()
            .addQueryParameter("page", page.toString())
            .build()
        return GET(url, headers)
    }

    override fun popularMangaParse(response: Response): MangasPage {
        val document = response.asJsoup()
        val mangas = document.select(".full--comic__list .full--comic__item").map {
            SManga.create().apply {
                title = it.selectFirst(".full--comic__title")!!.text()
                setUrlWithoutDomain(it.absUrl("href"))
                thumbnail_url = it.selectFirst("img.full--comic__thum")?.absUrl("data-src")
            }
        }
        val hasNextPage = document.selectFirst(".pagenation__item:not(.is-hidde) .pagenation__item__link--next") != null
        return MangasPage(mangas, hasNextPage)
    }

    override fun searchMangaRequest(page: Int, query: String, filters: FilterList): Request {
        val url = "$baseUrl/search".toHttpUrl().newBuilder()
            .addQueryParameter("q", query)
            .addQueryParameter("page", page.toString())
            .build()
        return GET(url, headers)
    }

    override fun searchMangaParse(response: Response): MangasPage = popularMangaParse(response)

    override fun mangaDetailsParse(response: Response): SManga {
        val document = response.asJsoup()
        return SManga.create().apply {
            title = document.selectFirst(".detail--title")!!.text()
            author = document.select(".detail__author__item").joinToString { it.text() }
            description = document.selectFirst(".detail--discription")?.text()
            thumbnail_url = document.selectFirst(".detail-catch__img")?.absUrl("src")
        }
    }

    override fun chapterListParse(response: Response): List<SChapter> {
        val chapters = mutableListOf<SChapter>()
        var document = response.asJsoup()

        while (true) {
            val pageChapters = document.select(".detail--product__list a.detail--product__item").map {
                SChapter.create().apply {
                    name = it.selectFirst(".detail--product__item__title")!!.text()
                    setUrlWithoutDomain(it.absUrl("href"))
                    date_upload = dateFormat.tryParse(it.selectFirst(".detail--product__item__sdate")?.text())
                }
            }
            chapters.addAll(pageChapters)

            val nextUrl = document.selectFirst(".pagenation__item__link--next")?.absUrl("href")
            if (nextUrl.isNullOrEmpty()) {
                break
            }

            val request = GET(nextUrl, headers)
            val nextResponse = client.newCall(request).execute()
            document = nextResponse.asJsoup()
        }
        return chapters
    }

    @Serializable
    class CPhpResponse(
        val url: String,
    )

    override fun pageListParse(response: Response): List<Page> {
        val cid = response.request.url.queryParameter("cid")
        val cUrl = "$baseUrl/api/viewer/c".toHttpUrl().newBuilder()
            .addQueryParameter("cid", cid)
            .build()

        val cRequest = GET(cUrl, headers)
        val cResponse = client.newCall(cRequest).execute()
        val cPhp = cResponse.parseAs<CPhpResponse>().url

        return fetchPages(cPhp, headers, client)
    }

    override fun imageUrlParse(response: Response): String = response.request.url.toString()

    override fun latestUpdatesRequest(page: Int): Request = throw UnsupportedOperationException()
    override fun latestUpdatesParse(response: Response): MangasPage = throw UnsupportedOperationException()
}
