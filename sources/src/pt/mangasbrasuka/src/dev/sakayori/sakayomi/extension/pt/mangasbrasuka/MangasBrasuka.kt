package dev.sakayori.sakayomi.extension.pt.mangasbrasuka

import dev.sakayori.sakayomi.multisrc.madara.Madara
import dev.sakayori.sakayomi.network.interceptor.rateLimit
import java.text.SimpleDateFormat
import java.util.Locale

class MangasBrasuka :
    Madara(
        "Mangas Brasuka",
        "https://mangasbrasuka.com.br",
        "pt-BR",
        SimpleDateFormat("MM dd, yyyy", Locale("pt", "BR")),
    ) {
    override val client = super.client.newBuilder()
        .rateLimit(2)
        .build()

    override val useLoadMoreRequest = LoadMoreStrategy.Never

    override val useNewChapterEndpoint = true
}
