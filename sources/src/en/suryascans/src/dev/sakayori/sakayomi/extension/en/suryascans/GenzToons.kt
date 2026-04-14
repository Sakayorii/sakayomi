package dev.sakayori.sakayomi.extension.en.suryascans

import dev.sakayori.sakayomi.multisrc.keyoapp.Keyoapp
import dev.sakayori.sakayomi.network.interceptor.rateLimit
import dev.sakayori.sakayomi.source.model.FilterList
import java.util.concurrent.TimeUnit

class GenzToons :
    Keyoapp(
        "Genz Toons",
        "https://genzupdates.com",
        "en",
    ) {

    override val client = super.client.newBuilder()
        .rateLimit(3)
        .connectTimeout(90, TimeUnit.SECONDS)
        .writeTimeout(90, TimeUnit.SECONDS)
        .readTimeout(90, TimeUnit.SECONDS)
        .build()

    override fun fetchPopularManga(page: Int) = fetchSearchManga(page, "", FilterList())
}
