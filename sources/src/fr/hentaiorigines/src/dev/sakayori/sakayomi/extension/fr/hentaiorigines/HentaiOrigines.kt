package dev.sakayori.sakayomi.extension.fr.hentaiorigines

import dev.sakayori.sakayomi.multisrc.madara.Madara
import dev.sakayori.sakayomi.network.POST
import okhttp3.Request
import java.text.SimpleDateFormat
import java.util.Locale

class HentaiOrigines :
    Madara(
        "Hentai Origines",
        "https://hentai-origines.fr",
        "fr",
        dateFormat = SimpleDateFormat("MMMM dd, yyyy", Locale("fr")),
    ) {
    override val mangaDetailsSelectorAuthor = "div.manga-authors a"
    override val mangaDetailsSelectorDescription = "div.summary__content"
    override val seriesTypeSelector = ".post-title span"

    override val useLoadMoreRequest = LoadMoreStrategy.Always
    override val useNewChapterEndpoint = true

    override fun xhrChaptersRequest(mangaUrl: String): Request = POST("$mangaUrl/ajax/chapters/", xhrHeaders)
}
