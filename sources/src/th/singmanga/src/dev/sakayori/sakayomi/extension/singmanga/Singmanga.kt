package dev.sakayori.sakayomi.extension.th.singmanga

import dev.sakayori.sakayomi.multisrc.mangathemesia.MangaThemesia
import dev.sakayori.sakayomi.source.model.FilterList

class Singmanga : MangaThemesia("SingManga", "https://www.sing-manga.com", "th") {
    override fun getFilterList() = FilterList()
}
