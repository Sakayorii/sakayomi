package dev.sakayori.sakayomi.extension.id.kiryuu

import dev.sakayori.sakayomi.multisrc.natsuid.NatsuId
import dev.sakayori.sakayomi.network.GET
import dev.sakayori.sakayomi.network.interceptor.rateLimit
import dev.sakayori.sakayomi.source.model.SManga
import okhttp3.OkHttpClient
import okhttp3.Request

class Kiryuu :
    NatsuId(
        "Kiryuu",
        "id",
        "https://v2.kiryuu.to",
    ) {
    // Formerly "Kiryuu (WP Manga Stream)"
    override val id = 3639673976007021338

    override fun OkHttpClient.Builder.customizeClient() = rateLimit(4)

    override fun chapterListRequest(manga: SManga): Request {
        val url = super.chapterListRequest(manga).url.newBuilder()
            .setQueryParameter("page", "1")
            .build()

        return GET(url, headers)
    }
}
