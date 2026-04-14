package dev.sakayori.sakayomi.extension.ar.yokai

import dev.sakayori.sakayomi.multisrc.zeistmanga.ZeistManga
import dev.sakayori.sakayomi.multisrc.zeistmanga.ZeistMangaDto
import dev.sakayori.sakayomi.network.GET
import dev.sakayori.sakayomi.source.model.SChapter
import dev.sakayori.sakayomi.util.asJsoup
import kotlinx.serialization.json.decodeFromStream
import okhttp3.Response

class Yokai : ZeistManga("Yokai", "https://yokai-team.blogspot.com", "ar") {

    // ============================== Chapters ==============================
    override fun chapterListParse(response: Response): List<SChapter> {
        val document = response.use { it.asJsoup() }

        val url = getChapterFeedUrl(document)

        val result = client.newCall(GET(url, headers)).execute()
            .use { json.decodeFromStream<ZeistMangaDto>(it.body.byteStream()) }

        val originalList = result.feed?.entry
            ?.filter { it.category.orEmpty().any { category -> category.term == chapterCategory } }
            ?.map { it.toSChapter(baseUrl) }
            ?: throw Exception("Failed to parse from chapter API")

        val additionalChapters = document.select("div#download > div.index-list > a").map {
            SChapter.create().apply {
                setUrlWithoutDomain(it.attr("href"))
                val text = it.text().trim()
                name = text
                chapter_number = text.substringBefore(' ').toFloatOrNull() ?: 1F
            }
        }

        return originalList + additionalChapters
    }
}
