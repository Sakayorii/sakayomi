package dev.sakayori.sakayomi.extension.pt.hipercool

import dev.sakayori.sakayomi.multisrc.madara.Madara
import dev.sakayori.sakayomi.network.interceptor.rateLimit
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit

class Hipercool : Madara("HipercooL", "https://hiper.cool", "pt-BR") {

    // Migrated from a custom CMS to Madara.
    override val versionId = 2

    override val chapterUrlSuffix = ""

    override val client: OkHttpClient = super.client.newBuilder()
        .rateLimit(1, 2, TimeUnit.SECONDS)
        .build()
}
