package dev.sakayori.sakayomi.extension.pt.xxxyaoi

import dev.sakayori.sakayomi.multisrc.madara.Madara
import dev.sakayori.sakayomi.network.interceptor.rateLimit
import okhttp3.OkHttpClient
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.concurrent.TimeUnit

class XXXYaoi :
    Madara(
        "XXX Yaoi",
        "https://3xyaoi.com",
        "pt-BR",
        SimpleDateFormat("MMMM dd, yyyy", Locale("pt", "BR")),
    ) {

    override val client: OkHttpClient = super.client.newBuilder()
        .rateLimit(1, 2, TimeUnit.SECONDS)
        .build()

    override val useNewChapterEndpoint = true

    override val mangaSubString = "bl"

    override val mangaDetailsSelectorAuthor = mangaDetailsSelectorArtist

    override val mangaDetailsSelectorStatus = "div.post-content_item:contains(Status) > div.summary-content"
}
