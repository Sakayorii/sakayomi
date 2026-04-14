package dev.sakayori.sakayomi.extension.ar.mangalionz

import dev.sakayori.sakayomi.multisrc.madara.Madara
import dev.sakayori.sakayomi.network.POST
import dev.sakayori.sakayomi.source.model.FilterList
import dev.sakayori.sakayomi.source.model.MangasPage
import dev.sakayori.sakayomi.source.model.SManga
import Sakayorii.utils.parseAs
import kotlinx.serialization.Serializable
import okhttp3.FormBody
import okhttp3.Request
import okhttp3.Response
import java.text.SimpleDateFormat
import java.util.Locale

class MangaLionz :
    Madara(
        "MangaLionz",
        "https://manga-lionz.org",
        "ar",
        dateFormat = SimpleDateFormat("MMMM d, yyyy", Locale("ar")),
    ) {

    override val useLoadMoreRequest = LoadMoreStrategy.Always

    override val chapterUrlSuffix = ""

    override fun searchMangaRequest(
        page: Int,
        query: String,
        filters: FilterList,
    ): Request = if (query.isNotBlank()) {
        POST(
            "$baseUrl/wp-admin/admin-ajax.php",
            headers,
            FormBody
                .Builder()
                .add("action", "wp-manga-search-manga")
                .add("title", query)
                .build(),
        )
    } else {
        super.searchMangaRequest(page, query, filters)
    }

    override fun searchMangaParse(response: Response): MangasPage {
        val contentType = response.header("Content-Type")
        return contentType?.takeIf { it.startsWith("application/json") }?.let {
            val dto = response.parseAs<SearchResponseDto>()

            if (!dto.success) {
                MangasPage(emptyList(), false)
            } else {
                val manga = dto.data.map {
                    SManga.create().apply {
                        setUrlWithoutDomain(it.url)
                        title = it.title
                    }
                }
                MangasPage(manga, false)
            }
        } ?: super.searchMangaParse(response)
    }

    @Serializable
    data class SearchResponseDto(
        val data: List<SearchEntryDto>,
        val success: Boolean,
    )

    @Serializable
    data class SearchEntryDto(
        val url: String = "",
        val title: String = "",
    )
}
