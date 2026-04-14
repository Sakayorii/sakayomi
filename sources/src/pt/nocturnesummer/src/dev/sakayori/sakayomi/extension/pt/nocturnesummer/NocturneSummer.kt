package dev.sakayori.sakayomi.extension.pt.nocturnesummer

import dev.sakayori.sakayomi.multisrc.madara.Madara
import dev.sakayori.sakayomi.network.interceptor.rateLimit
import dev.sakayori.sakayomi.source.model.SChapter
import okhttp3.OkHttpClient
import okhttp3.Response
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.concurrent.TimeUnit

class NocturneSummer :
    Madara(
        "Nocturne Summer",
        "https://nocfsb.com",
        "pt-BR",
        SimpleDateFormat("dd 'de' MMMMM 'de' yyyy", Locale("pt", "BR")),
    ) {

    override val client: OkHttpClient = super.client.newBuilder()
        .rateLimit(1, 2, TimeUnit.SECONDS)
        .build()

    override val useNewChapterEndpoint = true

    override val mangaDetailsSelectorStatus = "div.post-content_item:contains(Estado) > div.summary-content"

    override fun chapterListParse(response: Response): List<SChapter> = super.chapterListParse(response)
        .sortedBy(SChapter::name)
        .reversed()
}
