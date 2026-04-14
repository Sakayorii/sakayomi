package dev.sakayori.sakayomi.extension.pt.temakimangas

import dev.sakayori.sakayomi.multisrc.zeistmanga.ZeistManga
import dev.sakayori.sakayomi.source.model.SManga
import dev.sakayori.sakayomi.util.asJsoup
import okhttp3.Response

class TemakiMangas :
    ZeistManga(
        "Temaki mangás",
        "https://temakimangas.blogspot.com",
        "pt-BR",
    ) {
    override val popularMangaSelector = "#PopularPosts2 article"
    override val popularMangaSelectorTitle = "h3"
    override val popularMangaSelectorUrl = "h3 a"

    override fun mangaDetailsParse(response: Response) = SManga.create().apply {
        val document = response.asJsoup()
        val header = document.selectFirst("header")!!
        description = document.selectFirst("#synopsis")?.text()
        thumbnail_url = header.selectFirst(".thumb")?.absUrl("src")
        title = header.selectFirst("h1")!!.text()
        header.selectFirst("[data-status]")?.text()?.let {
            status = when (it.lowercase()) {
                "dropado" -> SManga.CANCELLED
                "finalizada" -> SManga.COMPLETED
                else -> SManga.UNKNOWN
            }
        }
        genre = document.select("dt:contains(Genre) + dd a").joinToString { it.ownText() }
    }

    override val pageListSelector = "#reader div.separator"
}
