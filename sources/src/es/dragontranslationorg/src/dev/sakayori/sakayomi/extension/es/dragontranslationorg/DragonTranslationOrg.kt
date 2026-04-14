package dev.sakayori.sakayomi.extension.es.dragontranslationorg

import dev.sakayori.sakayomi.multisrc.madara.Madara
import dev.sakayori.sakayomi.network.interceptor.rateLimit
import okhttp3.OkHttpClient
import java.text.SimpleDateFormat
import java.util.Locale

class DragonTranslationOrg :
    Madara(
        "DragonTranslation.org",
        "https://dragontranslation.org",
        "es",
        SimpleDateFormat("MMMM dd, yyyy", Locale("es")),
    ) {
    override val useLoadMoreRequest = LoadMoreStrategy.Always

    override val client: OkHttpClient = super.client.newBuilder()
        .rateLimit(3)
        .build()
}
