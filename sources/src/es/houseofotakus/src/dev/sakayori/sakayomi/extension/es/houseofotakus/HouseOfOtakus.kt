package dev.sakayori.sakayomi.extension.es.houseofotakus

import dev.sakayori.sakayomi.multisrc.madara.Madara
import java.text.SimpleDateFormat
import java.util.Locale

class HouseOfOtakus :
    Madara(
        "House Of Otakus",
        "https://houseofotakusv2.xyz",
        "es",
        dateFormat = SimpleDateFormat("MMMM dd, yyyy", Locale("es")),
    ) {
    override val useLoadMoreRequest = LoadMoreStrategy.Always
    override val useNewChapterEndpoint = true
}
