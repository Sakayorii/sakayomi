package dev.sakayori.sakayomi.extension.id.shiyurasub

import dev.sakayori.sakayomi.multisrc.zeistmanga.ZeistManga
import dev.sakayori.sakayomi.source.model.MangasPage
import okhttp3.Request
import okhttp3.Response

class ShiyuraSub : ZeistManga("ShiyuraSub", "https://shiyurasub.blogspot.com", "id") {

    override val hasFilters = true
    override val hasLanguageFilter = false

    override val supportsLatest = false

    override fun popularMangaRequest(page: Int): Request = latestUpdatesRequest(page)
    override fun popularMangaParse(response: Response): MangasPage = latestUpdatesParse(response)

    override val mangaDetailsSelectorDescription = "#synopsis ~ p"
}
