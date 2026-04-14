package dev.sakayori.sakayomi.extension.en.kappabeast

import dev.sakayori.sakayomi.multisrc.mangathemesia.MangaThemesia
import dev.sakayori.sakayomi.network.interceptor.rateLimit
import org.jsoup.nodes.Document

class KappaBeast :
    MangaThemesia(
        "Kappa Beast",
        "https://kappabeast.com",
        "en",
    ) {
    override val client = super.client.newBuilder()
        .rateLimit(3)
        .build()

    override val pageSelector = ".epcontent.entry-content img"

    override fun parseGenres(document: Document): List<GenreData> = document.select("li:has(input[id*='genre'])").map { li ->
        GenreData(
            li.selectFirst("label")!!.text(),
            li.selectFirst("input[type=checkbox]")!!.attr("value"),
        )
    }
}
