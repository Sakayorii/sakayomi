package dev.sakayori.sakayomi.extension.ja.yanmaga

import dev.sakayori.sakayomi.source.SourceFactory

class YanmagaFactory : SourceFactory {
    override fun createSources() = listOf(
        YanmagaComics(),
        YanmagaGravures(),
    )
}
