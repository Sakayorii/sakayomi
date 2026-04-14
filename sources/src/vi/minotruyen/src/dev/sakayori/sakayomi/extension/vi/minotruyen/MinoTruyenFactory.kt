package dev.sakayori.sakayomi.extension.vi.minotruyen

import dev.sakayori.sakayomi.source.Source
import dev.sakayori.sakayomi.source.SourceFactory

class MinoTruyenFactory : SourceFactory {
    override fun createSources(): List<Source> = listOf(
        MinoTruyen("MinoTruyen Manga", "manga"),
        MinoTruyen("MinoTruyen Comics", "comics"),
        MinoTruyen("MinoTruyen Hentai", "hentai"),
    )
}
