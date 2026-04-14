package dev.sakayori.sakayomi.extension.pt.galaxscanlator

import dev.sakayori.sakayomi.multisrc.zeistmanga.ZeistManga
import dev.sakayori.sakayomi.network.interceptor.rateLimit
import java.util.concurrent.TimeUnit

class GalaxScanlator :
    ZeistManga(
        "GALAX Scans",
        "https://galaxscanlator.blogspot.com",
        "pt-BR",
    ) {
    override val client = super.client.newBuilder()
        .rateLimit(5, 2, TimeUnit.SECONDS)
        .build()

    override val popularMangaSelector = "#PopularPosts2 article"
    override val popularMangaSelectorTitle = "h3"
    override val popularMangaSelectorUrl = "a"

    override val mangaDetailsSelector = ".grid.gta-series"
    override val mangaDetailsSelectorGenres = "dt:contains(Genre) + dd a[rel=tag]"

    override val useNewChapterFeed = true
    override val chapterCategory = "Capítulo"
    override val pageListSelector = "#reader"

    override val hasFilters = true
    override val hasLanguageFilter = false
}
