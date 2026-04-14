package dev.sakayori.sakayomi.extension.ar.mangatuk

import dev.sakayori.sakayomi.multisrc.madara.Madara
import java.text.SimpleDateFormat
import java.util.Locale

class MangaTuk :
    Madara(
        "MangaTuk",
        "https://mangatuk.com",
        "ar",
        dateFormat = SimpleDateFormat("MMMM dd, yyyy", Locale("ar")),
    ) {
    override val useNewChapterEndpoint = true

    override val useLoadMoreRequest = LoadMoreStrategy.Never
}
