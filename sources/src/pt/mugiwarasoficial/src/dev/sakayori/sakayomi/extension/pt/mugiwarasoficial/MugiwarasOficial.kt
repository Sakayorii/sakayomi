package dev.sakayori.sakayomi.extension.pt.mugiwarasoficial

import dev.sakayori.sakayomi.multisrc.madara.Madara
import java.text.SimpleDateFormat
import java.util.Locale

class MugiwarasOficial :
    Madara(
        "Mugiwaras Oficial",
        "https://mugiwarasoficial.com",
        "pt-BR",
        SimpleDateFormat("d 'de' MMM 'de' yyyy", Locale("pt", "BR")),
    ) {
    override val useNewChapterEndpoint = true
    override val useLoadMoreRequest = LoadMoreStrategy.Always
    override val mangaDetailsSelectorStatus = "div.summary-heading:contains(Estado) + .summary-content"
}
