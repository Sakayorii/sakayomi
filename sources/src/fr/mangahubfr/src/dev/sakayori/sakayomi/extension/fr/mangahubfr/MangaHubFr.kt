package dev.sakayori.sakayomi.extension.fr.mangahubfr

import dev.sakayori.sakayomi.multisrc.madara.Madara
import dev.sakayori.sakayomi.source.model.Page
import org.jsoup.nodes.Document
import java.text.SimpleDateFormat
import java.util.Locale

class MangaHubFr : Madara("MangaHub.fr", "https://mangahub.fr", "fr", dateFormat = SimpleDateFormat("d MMMM yyyy", Locale.FRENCH)) {
    // Only display chapters which don't have Premium
    override fun chapterListSelector() = "li.wp-manga-chapter:not(.vip-permission)"

    override val chapterUrlSuffix = ""

    override fun pageListParse(document: Document): List<Page> {
        launchIO { countViews(document) }

        return document.select(pageListParseSelector)
            .mapIndexed { index, element ->
                // Had to add trim because of white space in source.
                val imageUrl = element.select("img").attr("abs:src").trim()
                Page(index, document.location(), imageUrl)
            }
    }
}
