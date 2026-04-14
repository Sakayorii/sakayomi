package dev.sakayori.sakayomi.extension.ja.comicgrast

import dev.sakayori.sakayomi.network.GET
import dev.sakayori.sakayomi.source.model.FilterList
import dev.sakayori.sakayomi.source.model.MangasPage
import dev.sakayori.sakayomi.source.model.Page
import dev.sakayori.sakayomi.source.model.SChapter
import dev.sakayori.sakayomi.source.model.SManga
import dev.sakayori.sakayomi.source.online.HttpSource
import dev.sakayori.sakayomi.util.asJsoup
import Sakayorii.utils.parseAs
import Sakayorii.utils.tryParse
import okhttp3.HttpUrl.Companion.toHttpUrl
import okhttp3.Request
import okhttp3.Response
import java.text.SimpleDateFormat
import java.util.Locale

class ComicGrast : HttpSource() {
    override val name = "Comic Grast"
    override val baseUrl = "https://novema.jp"
    override val lang = "ja"
    override val supportsLatest = false

    private val dateFormat = SimpleDateFormat("yyyy/MM/dd", Locale.ROOT)

    override val client = network.cloudflareClient.newBuilder()
        .addInterceptor(ImageInterceptor())
        .build()

    override fun popularMangaRequest(page: Int): Request = GET("$baseUrl/comic/serial/$page", headers)

    override fun popularMangaParse(response: Response): MangasPage {
        val document = response.asJsoup()
        val mangas = document.select("ul.comicList > li").map { element ->
            SManga.create().apply {
                title = element.select("dl > dt > p.line-clamp.n2").text()
                setUrlWithoutDomain(element.selectFirst("a")!!.absUrl("href"))
                thumbnail_url = element.selectFirst("img")?.absUrl("data-src")
            }
        }
        val hasNextPage = document.selectFirst(".pageList li.next") != null
        return MangasPage(mangas, hasNextPage)
    }

    override fun searchMangaRequest(page: Int, query: String, filters: FilterList): Request {
        val url = "$baseUrl/comic/search/$page".toHttpUrl().newBuilder()
            .addQueryParameter("type", "0")
            .addQueryParameter("word", query)
            .build()
        return GET(url, headers)
    }

    override fun searchMangaParse(response: Response): MangasPage = popularMangaParse(response)

    override fun mangaDetailsParse(response: Response): SManga {
        val document = response.asJsoup()
        return SManga.create().apply {
            title = document.selectFirst(".comicTit")!!.text()
            description = document.selectFirst(".comicStory .txtAcd_text_inner")?.text()
            author = document.select(".credit li").joinToString { it.text() }
            genre = document.select(".topCategoryTag ul li a").joinToString { it.text().removePrefix("#") }
            document.selectFirst(".serialMainImage")?.absUrl("src")?.let { thumbnail_url = it }
        }
    }

    override fun chapterListParse(response: Response): List<SChapter> {
        val document = response.asJsoup()
        return document.select(".comicSerialList article a").mapNotNull { element ->
            val chapterUrl = element.absUrl("href").takeIf { it.isNotEmpty() } ?: return@mapNotNull null
            SChapter.create().apply {
                setUrlWithoutDomain(chapterUrl)
                name = element.select(".storyTitle").text()
                date_upload = element.select(".update").text().let { text ->
                    dateFormat.tryParse(text.replace(" 更新", ""))
                }
            }
        }
    }

    override fun pageListParse(response: Response): List<Page> {
        val document = response.asJsoup()
        val scriptData = document.selectFirst("#comic-data")?.data()
            ?: throw Exception("Comic data not found")

        val comicData = scriptData.parseAs<ComicData>()
        val indexUrl = "$baseUrl/img/serial-comic/${comicData.serialComicId}/${comicData.storyNumber}/content/index.json"
        val indexPages = client.newCall(GET(indexUrl, headers)).execute().parseAs<List<ComicPage>>()

        return indexPages.mapIndexed { i, page ->
            val url = "$baseUrl/img/serial-comic/${comicData.serialComicId}/${comicData.storyNumber}/content/${page.name}"
                .toHttpUrl()
                .newBuilder()
                .addQueryParameter("seed", page.seed)
                .addQueryParameter("size", page.size.toString())
                .build()
                .toString()
            Page(i, imageUrl = url)
        }
    }

    override fun latestUpdatesRequest(page: Int): Request = throw UnsupportedOperationException()
    override fun latestUpdatesParse(response: Response): MangasPage = throw UnsupportedOperationException()
    override fun imageUrlParse(response: Response): String = throw UnsupportedOperationException()
}
