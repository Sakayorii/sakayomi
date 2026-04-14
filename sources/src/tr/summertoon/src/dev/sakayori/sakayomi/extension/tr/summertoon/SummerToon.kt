package dev.sakayori.sakayomi.extension.tr.summertoon

import dev.sakayori.sakayomi.multisrc.madara.Madara
import dev.sakayori.sakayomi.network.interceptor.rateLimit
import java.text.SimpleDateFormat
import java.util.Locale

class SummerToon :
    Madara(
        "SummerToon",
        "https://summertoons.net",
        "tr",
        dateFormat = SimpleDateFormat("MMMM dd, yyyy", Locale("tr")),
    ) {
    override val client = super.client.newBuilder()
        .rateLimit(1, 1)
        .build()

    override val useLoadMoreRequest = LoadMoreStrategy.Always

    override val chapterUrlSelector = "div + a"
}
