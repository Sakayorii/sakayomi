package dev.sakayori.sakayomi.extension.en.meitoon

import dev.sakayori.sakayomi.multisrc.keyoapp.Keyoapp
import dev.sakayori.sakayomi.source.model.SManga
import org.jsoup.nodes.Document

class MeiToon :
    Keyoapp(
        "MeiToon",
        "https://meitoon.org",
        "en",
    ) {
    override fun popularMangaSelector(): String = ".series-splide .splide__slide:not(.splide__slide--clone)"

    override fun mangaDetailsParse(document: Document): SManga = super.mangaDetailsParse(document).apply {
        document.select("div:has(h1) a[href*='?genre=']")
            .joinToString { it.attr("title") }
            .takeIf { it.isNotEmpty() }
            ?.let {
                genre = genre?.plus(", $it") ?: it
            }
    }
}
