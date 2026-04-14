package dev.sakayori.sakayomi.extension.ja.ohtawebcomic

import dev.sakayori.sakayomi.network.GET
import dev.sakayori.sakayomi.network.asObservableSuccess
import dev.sakayori.sakayomi.source.model.FilterList
import dev.sakayori.sakayomi.source.model.MangasPage
import dev.sakayori.sakayomi.source.model.Page
import dev.sakayori.sakayomi.source.model.SChapter
import dev.sakayori.sakayomi.source.model.SManga
import dev.sakayori.sakayomi.source.online.ParsedHttpSource
import dev.sakayori.sakayomi.util.asJsoup
import Sakayorii.lib.speedbinb.SpeedBinbInterceptor
import Sakayorii.lib.speedbinb.SpeedBinbReader
import kotlinx.serialization.json.Json
import okhttp3.Response
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import rx.Observable
import uy.kohesive.injekt.Injekt
import uy.kohesive.injekt.api.get

class OhtaWebComic : ParsedHttpSource() {

    override val name = "Ohta Web Comic"

    override val baseUrl = "https://webcomic.ohtabooks.com"

    override val lang = "ja"

    override val supportsLatest = false

    private val json = Injekt.get<Json>()

    override val client = network.cloudflareClient.newBuilder()
        .addInterceptor(SpeedBinbInterceptor(json))
        .build()

    override fun headersBuilder() = super.headersBuilder()
        .add("Referer", "$baseUrl/")

    private lateinit var directory: List<Element>

    override fun fetchPopularManga(page: Int): Observable<MangasPage> = if (page == 1) {
        client.newCall(popularMangaRequest(page))
            .asObservableSuccess()
            .map { popularMangaParse(it) }
    } else {
        Observable.just(parseDirectory(page, ::popularMangaFromElement))
    }

    override fun popularMangaRequest(page: Int) = GET("$baseUrl/list/", headers)

    override fun popularMangaParse(response: Response): MangasPage {
        val document = response.asJsoup()

        directory = document.select(popularMangaSelector())
        return parseDirectory(1, ::popularMangaFromElement)
    }

    private fun parseDirectory(page: Int, parseFn: (element: Element) -> SManga): MangasPage {
        val endRange = minOf(page * 24, directory.size)
        val manga = directory.subList((page - 1) * 24, endRange).map { parseFn(it) }
        val hasNextPage = endRange < directory.lastIndex

        return MangasPage(manga, hasNextPage)
    }

    override fun popularMangaSelector() = ".bnrList ul li a"

    override fun popularMangaFromElement(element: Element) = SManga.create().apply {
        setUrlWithoutDomain(element.attr("href"))
        title = element.selectFirst(".title")!!.text()
        thumbnail_url = element.selectFirst(".pic img")?.absUrl("src")
    }

    override fun popularMangaNextPageSelector() = throw UnsupportedOperationException()

    override fun latestUpdatesRequest(page: Int) = throw UnsupportedOperationException()

    override fun latestUpdatesSelector() = throw UnsupportedOperationException()

    override fun latestUpdatesFromElement(element: Element) = throw UnsupportedOperationException()

    override fun latestUpdatesNextPageSelector() = throw UnsupportedOperationException()

    override fun fetchSearchManga(page: Int, query: String, filters: FilterList): Observable<MangasPage> = if (page == 1) {
        client.newCall(searchMangaRequest(page, query, filters))
            .asObservableSuccess()
            .map { searchMangaParse(it, query) }
    } else {
        Observable.just(parseDirectory(page, ::searchMangaFromElement))
    }

    override fun searchMangaRequest(page: Int, query: String, filters: FilterList) = popularMangaRequest(page)

    override fun searchMangaParse(response: Response) = throw UnsupportedOperationException()

    private fun searchMangaParse(response: Response, query: String): MangasPage {
        val document = response.asJsoup()

        directory = document.select(searchMangaSelector())
            .filter { it ->
                it.selectFirst(".title")?.text()?.contains(query, true) == true
            }
        return parseDirectory(1, ::searchMangaFromElement)
    }

    override fun searchMangaSelector() = popularMangaSelector()

    override fun searchMangaFromElement(element: Element) = popularMangaFromElement(element)

    override fun searchMangaNextPageSelector() = throw UnsupportedOperationException()

    override fun mangaDetailsParse(document: Document) = SManga.create().apply {
        title = document.selectFirst("[itemprop=name]")!!.text()
        author = document.selectFirst("[itemprop=author]")?.text()
        thumbnail_url = document.selectFirst(".contentHeader")
            ?.attr("style")
            ?.substringAfter("background-image:url(")
            ?.substringBefore(");")
        description = buildString {
            var currentNode = document.selectFirst("h3.titleBoader:contains(作品について) + dl")
                ?: return@buildString

            while (true) {
                val nextSibling = currentNode.nextElementSibling()
                    ?: break

                if (nextSibling.nodeName() != "p") {
                    break
                }

                appendLine(nextSibling.text())
                currentNode = nextSibling
            }
        }
    }

    override fun chapterListParse(response: Response): List<SChapter> {
        val document = response.asJsoup()
        val chapters = document.select(chapterListSelector())
            .sortedByDescending {
                it.selectFirst("dt.number")!!.ownText().toInt()
            }
            .map { chapterFromElement(it) }

        if (chapters.isNotEmpty()) {
            return chapters
        }

        return document.select(".headBtnList a[onclick*=openBook]")
            .map {
                SChapter.create().apply {
                    url = "/contents/${it.getChapterId()}"
                    name = it.ownText()
                }
            }
    }

    override fun chapterListSelector() = ".backnumberList a[onclick*=openBook]"

    override fun chapterFromElement(element: Element) = SChapter.create().apply {
        url = "/contents/${element.getChapterId()}"
        name = element.selectFirst("div.title")!!.text()
    }

    private val reader by lazy { SpeedBinbReader(client, headers, json, true) }

    override fun pageListRequest(chapter: SChapter) = GET("https://www.yondemill.jp${chapter.url}?view=1&u0=1", headers)

    override fun pageListParse(document: Document): List<Page> {
        val readerUrl = document.selectFirst("script:containsData(location.href)")!!
            .data()
            .substringAfter("location.href='")
            .substringBefore("';")
        val headers = headers.newBuilder()
            .set("Referer", document.location())
            .build()
        val readerResponse = client.newCall(GET(readerUrl, headers)).execute()

        return reader.pageListParse(readerResponse)
    }

    override fun imageUrlParse(document: Document) = throw UnsupportedOperationException()
}

private fun Element.getChapterId(): String = attr("onclick")
    .substringAfter("openBook('")
    .substringBefore("')")
