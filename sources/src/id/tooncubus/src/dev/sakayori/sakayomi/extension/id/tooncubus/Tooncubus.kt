package dev.sakayori.sakayomi.extension.id.tooncubus

import dev.sakayori.sakayomi.multisrc.zeistmanga.ZeistManga
import dev.sakayori.sakayomi.network.GET
import dev.sakayori.sakayomi.source.model.SChapter
import dev.sakayori.sakayomi.util.asJsoup
import okhttp3.Response

class Tooncubus : ZeistManga("Tooncubus", "https://www.tooncubus.top", "id") {

    override val pageListSelector = "div.check-box center"

    override fun chapterListParse(response: Response): List<SChapter> = response.asJsoup().selectFirst("ul.series-chapterlist")!!.select("div.flexch-infoz").map { element ->
        SChapter.create().apply {
            name = element.select("span").text()
            url = element.select("a").attr("href") // The website uses another domain for reading
        }
    }

    override fun pageListRequest(chapter: SChapter) = GET(chapter.url, headers)

    override fun getChapterUrl(chapter: SChapter) = chapter.url
}
