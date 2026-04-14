package dev.sakayori.sakayomi.extension.tr.mikrokosmosfansub

import dev.sakayori.sakayomi.multisrc.zeistmanga.ZeistManga
import dev.sakayori.sakayomi.source.model.Page
import dev.sakayori.sakayomi.util.asJsoup
import okhttp3.Response
import org.jsoup.Jsoup

class MikrokosmosFansub : ZeistManga("Mikrokosmos Fansub", "https://mikrokosmosfb.blogspot.com", "tr") {

    override val pageListSelector = "div.check-box script:containsData(content)"

    override fun pageListParse(response: Response): List<Page> {
        val document = response.asJsoup()
        val script = document.select(pageListSelector)
        val content = script.html().substringAfter("const content = `").substringBefore("`;")
        val images = Jsoup.parse(content).select("a")
        return images.select("img[src]").mapIndexed { i, img ->
            Page(i, "", img.attr("abs:src"))
        }
    }
}
