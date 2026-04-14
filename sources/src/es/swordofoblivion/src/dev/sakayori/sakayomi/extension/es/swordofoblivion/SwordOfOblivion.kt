package dev.sakayori.sakayomi.extension.es.swordofoblivion

import dev.sakayori.sakayomi.multisrc.madara.Madara
import java.text.SimpleDateFormat
import java.util.Locale

class SwordOfOblivion :
    Madara(
        "Sword Of Oblivion",
        "https://swordofoblivion.com",
        "es",
        dateFormat = SimpleDateFormat("MMMM dd, yyyy", Locale("es")),
    ) {
    override val useLoadMoreRequest = LoadMoreStrategy.Never
    override val useNewChapterEndpoint = true
}
