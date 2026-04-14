package dev.sakayori.sakayomi.extension.en.readhaikyuumangaonline

import dev.sakayori.sakayomi.multisrc.mangacatalog.MangaCatalog

class ReadHaikyuuMangaOnline : MangaCatalog("Read Haikyuu!! Manga Online", "https://ww9.readhaikyuu.com", "en") {
    override val sourceList = listOf(
        Pair("Haikyuu", "$baseUrl/manga/haikyuu/"),
        Pair("Haikyuu-bu!!", "$baseUrl/manga/haikyuu-bu/"),
        Pair("Haikyuu! x Nisekoi", "$baseUrl/manga/haikyuu-x-nisekoi/"),
        Pair("Let’s! Haikyu!?", "$baseUrl/manga/lets-haikyu/"),
        Pair("Colored", "$baseUrl/manga/haikyu-digital-colored-comics/"),
    )
}
