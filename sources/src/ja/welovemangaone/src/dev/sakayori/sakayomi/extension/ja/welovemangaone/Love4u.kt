package dev.sakayori.sakayomi.extension.ja.welovemangaone

import dev.sakayori.sakayomi.multisrc.fmreader.FMReader
import dev.sakayori.sakayomi.network.GET
import dev.sakayori.sakayomi.source.model.SChapter
import dev.sakayori.sakayomi.source.model.SManga
import dev.sakayori.sakayomi.util.asJsoup
import okhttp3.HttpUrl.Companion.toHttpUrl
import okhttp3.Request
import org.jsoup.nodes.Element
import java.util.Calendar

class Love4u : FMReader("Love4u", "https://love4u.net", "ja") {
    override val id = 1647179844716143786

    override fun latestUpdatesRequest(page: Int) = GET("$baseUrl/manga-list.html?page=$page&sort=last_update")

    override fun chapterListRequest(manga: SManga): Request {
        val mangaId = MID_URL_REGEX.find(manga.url)
            ?.groupValues?.get(1)
            ?: throw Exception("Could not find manga id")

        val xhrUrl = "$baseUrl/app/manga/controllers/cont.Listchapter.php".toHttpUrl().newBuilder()
            .addQueryParameter("mid", mangaId)
            .build()

        return GET(xhrUrl, headers)
    }

    override fun chapterFromElement(element: Element, mangaTitle: String): SChapter = SChapter.create().apply {
        element.let {
            setUrlWithoutDomain(it.attr("abs:href"))
            name = it.attr("title")
        }

        date_upload = element.select(chapterTimeSelector)
            .let { if (it.hasText()) parseChapterDate(it.text()) else 0 }
    }

    private fun parseChapterDate(date: String): Long {
        val value = date.split(' ')[dateValueIndex].toInt()
        val chapterDate = Calendar.getInstance().apply {
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)
        }

        when (date.split(' ')[dateWordIndex]) {
            "mins", "minutes" -> chapterDate.add(Calendar.MINUTE, -value)
            "hours" -> chapterDate.add(Calendar.HOUR_OF_DAY, -value)
            "days" -> chapterDate.add(Calendar.DATE, -value)
            "weeks" -> chapterDate.add(Calendar.DATE, -value * 7)
            "months" -> chapterDate.add(Calendar.MONTH, -value)
            "years" -> chapterDate.add(Calendar.YEAR, -value)
            else -> return 0
        }

        return chapterDate.timeInMillis
    }

    override fun pageListRequest(chapter: SChapter): Request {
        val request = super.pageListRequest(chapter)
        val response = client.newCall(request).execute()
        val document = response.asJsoup()

        val chapterId = document.selectFirst("#chapter")
            ?.`val`()
            ?: throw Exception("Could not find chapter id")

        val xhrUrl = "$baseUrl/app/manga/controllers/cont.listImg.php".toHttpUrl().newBuilder()
            .addQueryParameter("cid", chapterId)
            .build()

        return GET(xhrUrl, headers)
    }

    companion object {
        private val MID_URL_REGEX = "(\\d+)/".toRegex()
    }
}
