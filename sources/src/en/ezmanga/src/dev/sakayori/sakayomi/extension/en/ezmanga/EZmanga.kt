package dev.sakayori.sakayomi.extension.en.ezmanga

import dev.sakayori.sakayomi.multisrc.ezmanhwa.EZManhwa
import dev.sakayori.sakayomi.multisrc.ezmanhwa.EZManhwaSortFilter
import dev.sakayori.sakayomi.multisrc.ezmanhwa.EZManhwaStatusFilter
import dev.sakayori.sakayomi.multisrc.ezmanhwa.EZManhwaTypeFilter
import dev.sakayori.sakayomi.network.GET
import dev.sakayori.sakayomi.network.interceptor.rateLimit
import dev.sakayori.sakayomi.source.model.FilterList
import dev.sakayori.sakayomi.source.model.SChapter
import okhttp3.HttpUrl.Companion.toHttpUrl
import okhttp3.Request

class EZmanga : EZManhwa("EZmanga", "https://ezmanga.org", "https://vapi.ezmanga.org/api/v1") {

    override val versionId = 5
    override val client = network.cloudflareClient.newBuilder()
        .rateLimit(2)
        .build()

    override fun searchMangaRequest(page: Int, query: String, filters: FilterList): Request {
        val isSearch = query.isNotBlank()
        val endpoint = if (isSearch) "$apiUrl/series/search" else "$apiUrl/series"
        val url = endpoint.toHttpUrl().newBuilder().apply {
            addQueryParameter("page", page.toString())
            addQueryParameter("perPage", "20")
            if (isSearch) {
                addQueryParameter("q", query)
            } else {
                var sortAdded = false
                for (filter in filters) {
                    when (filter) {
                        is EZManhwaSortFilter -> {
                            addQueryParameter("sort", filter.value)
                            sortAdded = true
                        }
                        is EZManhwaStatusFilter -> if (filter.value.isNotBlank()) addQueryParameter("status", filter.value)
                        is EZManhwaTypeFilter -> if (filter.value.isNotBlank()) addQueryParameter("type", filter.value)
                        is EZmangaGenreFilter -> if (filter.value.isNotBlank()) addQueryParameter("genre", filter.value)
                        else -> {}
                    }
                }
                if (!sortAdded) addQueryParameter("sort", "latest")
            }
        }.build()
        return GET(url, headers)
    }

    override fun pageListRequest(chapter: SChapter): Request = if (chapter.url.contains("/chapters/")) {
        GET("$apiUrl/${chapter.url}", headers)
    } else {
        val path = chapter.url.removePrefix("/series/")
        val slash = path.indexOf('/')
        GET("$apiUrl/series/${path.substring(0, slash)}/chapters/${path.substring(slash + 1)}", headers)
    }

    override fun getFilterList() = FilterList(
        EZManhwaSortFilter(),
        EZManhwaStatusFilter(),
        EZManhwaTypeFilter(),
        EZmangaGenreFilter(),
    )
}
