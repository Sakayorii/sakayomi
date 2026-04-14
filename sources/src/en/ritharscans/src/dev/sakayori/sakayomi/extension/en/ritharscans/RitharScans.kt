package dev.sakayori.sakayomi.extension.en.ritharscans

import dev.sakayori.sakayomi.multisrc.keyoapp.Keyoapp
import dev.sakayori.sakayomi.network.GET
import dev.sakayori.sakayomi.source.model.FilterList
import dev.sakayori.sakayomi.source.model.MangasPage
import dev.sakayori.sakayomi.source.model.Page
import dev.sakayori.sakayomi.util.asJsoup
import Sakayorii.utils.parseAs
import kotlinx.serialization.Serializable
import okhttp3.HttpUrl.Companion.toHttpUrl
import okhttp3.Request
import okhttp3.Response
import org.jsoup.nodes.Document

class RitharScans : Keyoapp("RitharScans", "https://ritharscans.com", "en") {

    override fun popularMangaParse(response: Response): MangasPage {
        val mangas = super.popularMangaParse(response).mangas
            .distinctBy { it.url }

        return MangasPage(mangas, false)
    }

    override fun genresRequest() = GET("$baseUrl/search", headers)

    override fun parseGenres(document: Document): List<Genre> = document.select("[x-data*=genre] button").map {
        val name = it.text()
        val id = it.attr("wire:key")

        Genre(name, id)
    }

    override fun searchMangaRequest(page: Int, query: String, filters: FilterList): Request {
        val url = baseUrl.toHttpUrl().newBuilder().apply {
            addPathSegment("search")
            if (query.isNotBlank()) {
                addQueryParameter("title", query)
            }
            filters.firstOrNull { it is GenreList }?.also {
                val filter = it as GenreList
                filter.state
                    .filter { it.state }
                    .forEach { genre ->
                        addQueryParameter("genre", genre.id)
                    }
            }
        }.build()

        return GET(url, headers)
    }

    override fun searchMangaSelector() = "[wire:snapshot*=pages.search] button[tags]"

    override fun searchMangaParse(response: Response): MangasPage {
        runCatching { fetchGenres() }

        val mangas = response.asJsoup()
            .select(searchMangaSelector())
            .map(::searchMangaFromElement)

        return MangasPage(mangas, false)
    }

    override val descriptionSelector = "#expand_content"
    override val statusSelector = "[alt=Status]"
    override val typeSelector = "[alt=Type]"

    override fun pageListParse(document: Document): List<Page> {
        val (pages, baseLink) = document.selectFirst("[x-data*=pages]")!!.attr("x-data")
            .replace(spaces, "")
            .let {
                val pages = pagesRegex.find(it)!!.groupValues[1]
                    .replace("&quot;", "\"")
                    .parseAs<List<Path>>()

                val baseLink = linkRegex.find(
                    it.replace("\"", "'"),
                )!!.groupValues[1]

                pages to baseLink
            }

        return pages.mapIndexed { i, img ->
            Page(i, document.location(), baseLink + img.path)
        }
    }
}

private val spaces = Regex("\\s")
private val pagesRegex = Regex("pages:(\\[[^]]+])")
private val linkRegex = Regex("baseLink:'([^']+)'")

@Serializable
class Path(
    val path: String,
)
