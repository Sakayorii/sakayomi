package dev.sakayori.sakayomi.extension.id.manhwalandmom

import dev.sakayori.sakayomi.multisrc.mangathemesia.MangaThemesia
import dev.sakayori.sakayomi.network.interceptor.rateLimit
import okhttp3.OkHttpClient
import java.text.SimpleDateFormat
import java.util.Locale

class ManhwaLandMom :
    MangaThemesia(
        "ManhwaLand.mom",
        "https://02.manhwaland.land",
        "id",
        dateFormat = SimpleDateFormat("MMMM d, yyyy", Locale("id")),
    ) {

    override val client: OkHttpClient = super.client.newBuilder()
        .rateLimit(4)
        .build()
}
