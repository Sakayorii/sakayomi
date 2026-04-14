package dev.sakayori.sakayomi.extension.fr.mangacorporation

import dev.sakayori.sakayomi.multisrc.pizzareader.PizzaReader
import kotlinx.serialization.json.Json

class MangaCorporation : PizzaReader("Manga-Corporation", "https://manga-corporation.com", "fr") {
    override val json: Json = Json {
        coerceInputValues = true
        ignoreUnknownKeys = true
    }
}
