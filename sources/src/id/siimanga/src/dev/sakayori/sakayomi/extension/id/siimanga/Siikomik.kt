package dev.sakayori.sakayomi.extension.id.siimanga

import dev.sakayori.sakayomi.multisrc.madara.Madara
import dev.sakayori.sakayomi.source.model.SChapter
import org.jsoup.nodes.Element

class Siikomik :
    Madara(
        "Siikomik",
        "https://siikomik.net",
        "id",
    ) {
    override val versionId = 3

    override val mangaSubString = "komik"

    override fun chapterFromElement(element: Element): SChapter = super.chapterFromElement(element).apply {
        if (element.hasClass("premium") || element.hasClass("premium-block")) {
            name = "🔒 $name"
        }
    }
}
