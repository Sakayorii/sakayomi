package dev.sakayori.sakayomi.extension.es.gremorymangas

import dev.sakayori.sakayomi.multisrc.madara.Madara
import java.text.SimpleDateFormat
import java.util.Locale

class GremoryMangas :
    Madara(
        "Gremory Mangas",
        "https://gremoryhistorias.org",
        "es",
        dateFormat = SimpleDateFormat("MMMM dd, yyyy", Locale("es")),
    ) {
    override val useNewChapterEndpoint = true
}
