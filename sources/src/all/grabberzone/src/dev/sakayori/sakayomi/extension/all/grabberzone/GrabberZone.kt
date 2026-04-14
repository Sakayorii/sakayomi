package dev.sakayori.sakayomi.extension.all.grabberzone

import dev.sakayori.sakayomi.multisrc.madara.Madara
import dev.sakayori.sakayomi.source.model.SChapter
import org.jsoup.nodes.Element
import java.text.SimpleDateFormat
import java.util.Locale

class GrabberZone :
    Madara(
        "Grabber Zone",
        "https://grabber.zone",
        "all",
        SimpleDateFormat("dd.MM.yyyy", Locale.ENGLISH),
    ) {
    override val mangaSubString = "comics"

    override fun chapterFromElement(element: Element): SChapter = super.chapterFromElement(element).apply {
        name = element.selectFirst("a + a")!!.text()
    }
}
