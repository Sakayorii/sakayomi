package dev.sakayori.sakayomi.extension.all.hniscantrad

import dev.sakayori.sakayomi.multisrc.pizzareader.PizzaReader
import dev.sakayori.sakayomi.source.model.SManga
import kotlinx.serialization.json.Json

class HNIScantrad : PizzaReader("HNI-Scantrad", "https://hni-scantrad.net", "all") {
    override val json = Json {
        ignoreUnknownKeys = true
        coerceInputValues = true
    }

    override fun String.toStatus(): Int = if (isEmpty()) {
        SManga.UNKNOWN
    } else {
        when (substring(0, 7)) {
            "In cors" -> SManga.ONGOING
            "On goin" -> SManga.ONGOING
            "Complet" -> SManga.COMPLETED
            "Conclus" -> SManga.COMPLETED
            "Conclud" -> SManga.COMPLETED
            "Licenzi" -> SManga.LICENSED
            "License" -> SManga.LICENSED
            else -> SManga.UNKNOWN
        }
    }
}
