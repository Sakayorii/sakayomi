package dev.sakayori.sakayomi.extension.zh.manhuadui

import dev.sakayori.sakayomi.multisrc.sinmh.SinMH
import dev.sakayori.sakayomi.source.model.SChapter
import org.jsoup.nodes.Document

// This site blocks IP outside China
class YKMH : SinMH("优酷漫画", "https://www.ykmh.net") {
    override val id = 1637952806167036168

    override fun mangaDetailsParse(document: Document) = mangaDetailsParseDMZJStyle(document, hasBreadcrumb = false)

    override fun List<SChapter>.sortedDescending() = this
}
