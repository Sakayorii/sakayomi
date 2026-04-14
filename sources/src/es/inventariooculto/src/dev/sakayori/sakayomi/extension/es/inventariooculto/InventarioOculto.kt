package dev.sakayori.sakayomi.extension.es.inventariooculto

import dev.sakayori.sakayomi.multisrc.madara.Madara
import java.text.SimpleDateFormat
import java.util.Locale

class InventarioOculto :
    Madara(
        "Inventario Oculto",
        "https://inventariooculto.com",
        "es",
        SimpleDateFormat("dd MMMM, yyyy", Locale("es")),
    ) {
    override val useNewChapterEndpoint = true
}
