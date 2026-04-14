package dev.sakayori.sakayomi.extension.fr.mangasoriginesfr

import dev.sakayori.sakayomi.multisrc.madara.Madara
import dev.sakayori.sakayomi.network.POST
import dev.sakayori.sakayomi.source.model.SChapter
import dev.sakayori.sakayomi.util.asJsoup
import okhttp3.Response
import java.text.SimpleDateFormat
import java.util.Locale

class MangasOriginesFr :
    Madara(
        "Mangas-Origines.fr",
        "https://mangas-origines.fr",
        "fr",
        SimpleDateFormat("MMMM d, yyyy", Locale("fr")),
    ) {
    override val mangaSubString = "catalogues"

    override val useNewChapterEndpoint = true

    override fun chapterListParse(response: Response): List<SChapter> {
        val document = response.asJsoup()

        launchIO { countViews(document) }

        val chaptersWrapper = document.select("div[id^=manga-chapters-holder]")

        var chapterElements = document.select(chapterListSelector())

        if (chapterElements.isEmpty() && !chaptersWrapper.isNullOrEmpty()) {
            val mangaUrl = document.location().removeSuffix("/")

            val xhrRequest = POST("$mangaUrl/ajax/chapters/", xhrHeaders)
            val xhrResponse = client.newCall(xhrRequest).execute()

            chapterElements = xhrResponse.asJsoup().select(chapterListSelector())
            xhrResponse.close()
        }

        return chapterElements.map(::chapterFromElement)
    }

    // Manga Details Selectors
    override val mangaDetailsSelectorAuthor = "div.manga-authors > a"
    override val mangaDetailsSelectorDescription = "div.summary__content > p"
}
