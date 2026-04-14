package dev.sakayori.sakayomi.extension.all.qtoon

import dev.sakayori.sakayomi.source.SourceFactory

class QToonFactory : SourceFactory {
    override fun createSources() = listOf(
        QToon("en", "en-US"),
        QToon("es", "es-ES"),
        QToon("pt-BR", "pt-PT"),
    )
}
