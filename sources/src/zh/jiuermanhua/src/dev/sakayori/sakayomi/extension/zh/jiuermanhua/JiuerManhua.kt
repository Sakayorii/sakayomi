package dev.sakayori.sakayomi.extension.zh.jiuermanhua

import dev.sakayori.sakayomi.multisrc.sinmh.SinMH
import dev.sakayori.sakayomi.network.GET
import dev.sakayori.sakayomi.source.model.SChapter
import org.jsoup.nodes.Document

class JiuerManhua : SinMH("92漫画", "http://www.92mh.com") {

    override fun mangaDetailsParse(document: Document) = mangaDetailsParseDMZJStyle(document, hasBreadcrumb = false)

    override fun pageListRequest(chapter: SChapter) = GET(baseUrl + chapter.url, headers)
}
