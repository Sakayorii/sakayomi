package dev.sakayori.sakayomi.extension.pt.cerisescans

import dev.sakayori.sakayomi.multisrc.madara.Madara
import dev.sakayori.sakayomi.network.interceptor.rateLimit
import dev.sakayori.sakayomi.source.model.Page
import Sakayorii.utils.parseAs
import org.jsoup.nodes.Document
import java.text.SimpleDateFormat
import java.util.Locale

class CeriseScan :
    Madara(
        "Cerise Scan",
        "https://loverstoon.com",
        "pt-BR",
        SimpleDateFormat("dd.MM.yyyy", Locale.ROOT),
    ) {
    override val versionId: Int = 3

    override val client = super.client.newBuilder()
        .rateLimit(3, 2)
        .build()

    override val useLoadMoreRequest = LoadMoreStrategy.Always

    override val useNewChapterEndpoint = true

    override fun pageListParse(document: Document): List<Page> {
        val pages = document.selectFirst(".page-break script")?.data()
            ?.let { PAGE_REGEX.find(it)?.groupValues?.last() }
            ?: return emptyList()
        return pages.parseAs<List<String>>().mapIndexed { index, imageUrl ->
            Page(index, imageUrl = imageUrl)
        }
    }

    companion object {
        private val PAGE_REGEX = """content:\s+(\[[\s\S*]+\])""".toRegex()
    }
}
