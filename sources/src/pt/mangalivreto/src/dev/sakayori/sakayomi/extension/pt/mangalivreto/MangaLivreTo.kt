package dev.sakayori.sakayomi.extension.pt.mangalivreto

import dev.sakayori.sakayomi.multisrc.madara.Madara
import dev.sakayori.sakayomi.network.interceptor.rateLimit
import java.text.SimpleDateFormat
import java.util.Locale

class MangaLivreTo :
    Madara(
        "Manga Livre.to",
        "https://mangalivre.to",
        "pt-BR",
        SimpleDateFormat("dd 'de' MMMM 'de' yyyy", Locale("pt")),
    ) {
    override val client = super.client.newBuilder()
        .rateLimit(2)
        .build()

    override fun chapterListSelector() = ".listing-chapters-wrap .chapter-box"

    override fun chapterDateSelector() = ".chapter-date"
}
