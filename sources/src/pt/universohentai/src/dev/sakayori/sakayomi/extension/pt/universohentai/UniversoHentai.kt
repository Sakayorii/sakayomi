package dev.sakayori.sakayomi.extension.pt.universohentai

import dev.sakayori.sakayomi.multisrc.gattsu.Gattsu
import dev.sakayori.sakayomi.network.interceptor.rateLimit
import dev.sakayori.sakayomi.source.model.SChapter
import dev.sakayori.sakayomi.source.model.SManga
import dev.sakayori.sakayomi.util.asJsoup
import okhttp3.OkHttpClient
import okhttp3.Response
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import java.util.concurrent.TimeUnit

class UniversoHentai :
    Gattsu(
        "Universo Hentai",
        "https://universohentai.com",
        "pt-BR",
    ) {

    override val client: OkHttpClient = super.client.newBuilder()
        .rateLimit(1, 2, TimeUnit.SECONDS)
        .build()

    override fun latestUpdatesSelector() = "div.meio div.videos div.video a[href^=$baseUrl]:not(:has(span.selo-hd))"

    override fun latestUpdatesFromElement(element: Element): SManga = SManga.create().apply {
        title = element.selectFirst("span.video-titulo")!!.text().trim()
        thumbnail_url = element.selectFirst("img.wp-post-image")!!.attr("src")
        setUrlWithoutDomain(element.attr("href"))
    }

    override fun mangaDetailsParse(document: Document): SManga = SManga.create().apply {
        val postBox = document.selectFirst(chapterListSelector())!!

        title = postBox.select("h1.post-titulo").first()!!.text()
        author = postBox.select("ul.paginaPostItens li:contains(Artista) a").firstOrNull()?.text()
        genre = postBox.select("ul.paginaPostItens li:contains(Categorias) a")
            .joinToString(", ") { it.text() }
        status = SManga.COMPLETED
        thumbnail_url = postBox.select("div.paginaPostThumb > img.wp-post-image")
            .attr("src")
            .withoutSize()
    }

    override fun chapterListParse(response: Response): List<SChapter> {
        val document = response.asJsoup()

        return document.select(chapterListSelector())
            .map { chapterFromElement(it) }
    }

    override fun chapterListSelector() = "div.meio div.post[itemscope]:has(a[title=Abrir galeria])"

    override fun chapterFromElement(element: Element): SChapter = SChapter.create().apply {
        name = "Capítulo único"
        scanlator = element.select("ul.paginaPostItens li:contains(Tradutor) a").firstOrNull()?.text()
        date_upload = element.ownerDocument()!!.select("meta[property=article:published_time]").firstOrNull()
            ?.attr("content")
            .orEmpty()
            .toDate()
        setUrlWithoutDomain(element.selectFirst("a[title=Abrir galeria]")!!.attr("href"))
    }

    override fun pageListSelector() = "div.meio div.galeria div.galeria-foto a img"
}
