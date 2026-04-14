package dev.sakayori.sakayomi.extension.fr.epsilonscan

import dev.sakayori.sakayomi.multisrc.madara.Madara
import okhttp3.Headers
import java.text.SimpleDateFormat
import java.util.Locale

class EpsilonScan :
    Madara(
        "Epsilon Scan",
        "https://epsilonscan.to",
        "fr",
        SimpleDateFormat("dd/MM/yy", Locale.FRENCH),
    ) {
    // Site moved from MangaThemesia to Madara
    override val versionId = 2

    override fun headersBuilder(): Headers.Builder = super.headersBuilder().add("x-requested-with", "app.notSakayomi")

    override val useLoadMoreRequest = LoadMoreStrategy.Always

    override val useNewChapterEndpoint = true
}
