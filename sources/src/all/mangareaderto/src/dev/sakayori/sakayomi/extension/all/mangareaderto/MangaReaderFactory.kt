package dev.sakayori.sakayomi.extension.all.mangareaderto

import dev.sakayori.sakayomi.source.SourceFactory

class MangaReaderFactory : SourceFactory {
    override fun createSources() = arrayOf(
        Language("en"),
        Language("es", chapterInfix = "es-mx"),
        Language("fr"),
        Language("ja"),
        Language("ko"),
        Language("pt-BR", infix = "pt"),
        Language("zh"),
    ).map(::MangaReader)
}

data class Language(
    val code: String,
    val infix: String = code,
    val chapterInfix: String = code.lowercase(),
)
