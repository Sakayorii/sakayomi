package dev.sakayori.sakayomi.extension.en.coffeemanga

import dev.sakayori.sakayomi.multisrc.madara.Madara
import org.jsoup.nodes.Element

class CoffeeManga : Madara("Coffee Manga", "https://coffeemanga.ink", "en") {
    override val useNewChapterEndpoint = false

    override fun imageFromElement(element: Element): String? = when {
        element.attr("data-src").isNotBlank() -> element.attr("abs:data-src")
        element.attr("data-lazy-src").isNotBlank() -> element.attr("abs:data-lazy-src")
        element.attr("srcset").isNotBlank() -> element.attr("abs:srcset").getSrcSetImage()
        element.attr("data-cfsrc").isNotBlank() -> element.attr("abs:data-cfsrc")
        else -> element.attr("abs:src")
    }
}
