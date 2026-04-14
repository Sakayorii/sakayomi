package dev.sakayori.sakayomi.extension.pt.dreamscan

import dev.sakayori.sakayomi.multisrc.madara.Madara
import java.text.SimpleDateFormat
import java.util.Locale

class DreamScan :
    Madara(
        "Dream Scan",
        "https://fairydream.com.br",
        "pt-BR",
        SimpleDateFormat("MMMM d, yyyy", Locale("pt", "BR")),
    ) {
    override val id: Long = 2058412298484770949

    override val useNewChapterEndpoint = true
}
