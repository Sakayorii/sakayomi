package dev.sakayori.sakayomi.extension.es.begatranslation

import dev.sakayori.sakayomi.multisrc.madara.Madara
import dev.sakayori.sakayomi.source.model.SManga
import org.jsoup.nodes.Element
import java.text.SimpleDateFormat
import java.util.Locale

class BegaTranslation :
    Madara(
        "Bega Translation",
        "https://begatranslation.com",
        "es",
        SimpleDateFormat("dd MMMM, yyyy", Locale("es")),
    ) {
    override val useNewChapterEndpoint = true
    override val mangaSubString = "series"

    override fun popularMangaFromElement(element: Element): SManga = super.popularMangaFromElement(element).apply {
        thumbnail_url = thumbnail_url?.replaceFirst("-175x238", "")
    }
    override fun searchMangaFromElement(element: Element): SManga = super.searchMangaFromElement(element).apply {
        thumbnail_url = thumbnail_url?.replaceFirst("-193x278", "")
    }
}
