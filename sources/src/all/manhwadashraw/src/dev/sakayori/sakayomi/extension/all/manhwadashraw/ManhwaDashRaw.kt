package dev.sakayori.sakayomi.extension.all.manhwadashraw

import dev.sakayori.sakayomi.multisrc.madara.Madara
import dev.sakayori.sakayomi.source.model.MangasPage
import okhttp3.Response
import java.text.SimpleDateFormat
import java.util.Locale

class ManhwaDashRaw :
    Madara(
        "Manhwa-raw",
        "https://manhwa-raw.com",
        "all",
        dateFormat = SimpleDateFormat("dd/MM/yyy", Locale.ROOT),
    ) {
    override val useNewChapterEndpoint = true

    override val mangaDetailsSelectorStatus = "div.post-content_item:contains(Status) > div.summary-content"
    override val mangaDetailsSelectorDescription = "div.post-content_item:contains(Summary) div.summary-container"
    override val pageListParseSelector = "div.page-break img.wp-manga-chapter-img"

    override fun latestUpdatesParse(response: Response): MangasPage = MangasPage(super.latestUpdatesParse(response).mangas, true)
    override fun popularMangaParse(response: Response): MangasPage = MangasPage(super.popularMangaParse(response).mangas, true)
    override fun searchMangaParse(response: Response): MangasPage = MangasPage(super.searchMangaParse(response).mangas, true)
}
