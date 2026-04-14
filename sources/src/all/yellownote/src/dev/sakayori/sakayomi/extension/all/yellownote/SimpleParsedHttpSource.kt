package dev.sakayori.sakayomi.extension.all.yellownote

import dev.sakayori.sakayomi.source.model.SManga
import dev.sakayori.sakayomi.source.online.ParsedHttpSource
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element

abstract class SimpleParsedHttpSource : ParsedHttpSource() {

    abstract fun simpleMangaSelector(): String

    abstract fun simpleMangaFromElement(element: Element): SManga

    abstract fun simpleNextPageSelector(): String?

    // region popular
    override fun popularMangaSelector() = simpleMangaSelector()
    override fun popularMangaNextPageSelector() = simpleNextPageSelector()

    override fun popularMangaFromElement(element: Element) = simpleMangaFromElement(element)
    // endregion

    // region last
    override fun latestUpdatesSelector() = simpleMangaSelector()
    override fun latestUpdatesFromElement(element: Element) = simpleMangaFromElement(element)

    override fun latestUpdatesNextPageSelector() = simpleNextPageSelector()
    // endregion

    // region search
    override fun searchMangaSelector() = simpleMangaSelector()
    override fun searchMangaFromElement(element: Element) = simpleMangaFromElement(element)

    override fun searchMangaNextPageSelector() = simpleNextPageSelector()
    // endregion

    override fun chapterListSelector() = simpleMangaSelector()
    override fun imageUrlParse(document: Document): String = throw UnsupportedOperationException()
    // endregion
}
