package dev.sakayori.sakayomi.extension.pt.mangaonline

import dev.sakayori.sakayomi.multisrc.madara.Madara
import dev.sakayori.sakayomi.network.interceptor.rateLimit
import java.text.SimpleDateFormat
import java.util.Locale

class MangaOnline :
    Madara(
        "Manga Online",
        "https://mangaonline.red",
        "pt-BR",
        SimpleDateFormat("dd 'de' MMMM 'de' yyyy", Locale("pt")),
    ) {
    override val client = super.client.newBuilder()
        .rateLimit(2)
        .build()

    override val chapterUrlSuffix = ""
    override val useLoadMoreRequest = LoadMoreStrategy.Never
    override val useNewChapterEndpoint = true
}
