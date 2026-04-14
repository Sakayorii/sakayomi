package dev.sakayori.sakayomi.extension.en.armageddon

import dev.sakayori.sakayomi.multisrc.mangathemesia.MangaThemesia

class Armageddon :
    MangaThemesia(
        name = "Armageddon",
        baseUrl = "https://www.silentquill.net",
        lang = "en",
    ) {
    override val seriesTitleSelector = "h1.kdt8-left-title"

    override val seriesThumbnailSelector = ".kdt8-cover img"

    override val seriesDescriptionSelector = ".kdt8-synopsis"

    override val seriesGenreSelector = ".kdt8-genres a.kdt8-genre-tag"
}
