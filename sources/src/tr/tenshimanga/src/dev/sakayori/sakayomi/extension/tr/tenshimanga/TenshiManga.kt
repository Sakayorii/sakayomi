package dev.sakayori.sakayomi.extension.tr.tenshimanga

import dev.sakayori.sakayomi.multisrc.uzaymanga.UzayManga

class TenshiManga :
    UzayManga(
        name = "Tenshi Manga",
        baseUrl = "https://tenshimanga.com",
        lang = "tr",
        versionId = 1,
        cdnUrl = "https://tenshimangacdn4.efsaneler.can.re",
    )
