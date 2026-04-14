package dev.sakayori.sakayomi.extension.all.comikey

import dev.sakayori.sakayomi.source.SourceFactory

class ComikeyFactory : SourceFactory {
    override fun createSources() = listOf(
        Comikey("en"),
        Comikey("es"),
        Comikey("id"),
        Comikey("pt-BR"),
        Comikey("pt-BR", "Comikey Brasil", "https://br.comikey.com", defaultLanguage = "pt-BR"),
    )
}
