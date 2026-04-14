package dev.sakayori.sakayomi.extension.en.restscans

import dev.sakayori.sakayomi.multisrc.mangathemesia.MangaThemesia
import org.jsoup.nodes.Document

class RestScans :
    MangaThemesia(
        "Rest Scans",
        "https://restscans.com",
        "en",
    ) {
    override fun mangaDetailsParse(document: Document) = super.mangaDetailsParse(document).apply {
        description = document.select(seriesDescriptionSelector).apply { select(".rs-comments-wrapper").remove() }.joinToString("\n") { it.text() }.trim()
        val altName = document.selectFirst(seriesDetailsSelector)?.selectFirst(seriesAltNameSelector)?.ownText().takeIf { it.isNullOrBlank().not() }
        altName?.let {
            description = "$description\n\n$altNamePrefix$altName".trim()
        }
    }
}
