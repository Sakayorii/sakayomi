package dev.sakayori.sakayomi.extension.it.tuttoanimemanga

import dev.sakayori.sakayomi.multisrc.pizzareader.PizzaReader
import kotlinx.serialization.json.Json

class TuttoAnimeManga : PizzaReader("TuttoAnimeManga", "https://tuttoanimemanga.net", "it") {
    override val json = Json {
        ignoreUnknownKeys = true
        coerceInputValues = true
    }
}
