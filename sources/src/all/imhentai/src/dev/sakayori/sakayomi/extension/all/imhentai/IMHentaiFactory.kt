package dev.sakayori.sakayomi.extension.all.imhentai

import dev.sakayori.sakayomi.multisrc.galleryadults.GalleryAdults
import dev.sakayori.sakayomi.source.Source
import dev.sakayori.sakayomi.source.SourceFactory

class IMHentaiFactory : SourceFactory {

    override fun createSources(): List<Source> = listOf(
        IMHentai("en", GalleryAdults.LANGUAGE_ENGLISH),
        IMHentai("ja", GalleryAdults.LANGUAGE_JAPANESE),
        IMHentai("es", GalleryAdults.LANGUAGE_SPANISH),
        IMHentai("fr", GalleryAdults.LANGUAGE_FRENCH),
        IMHentai("ko", GalleryAdults.LANGUAGE_KOREAN),
        IMHentai("de", GalleryAdults.LANGUAGE_GERMAN),
        IMHentai("ru", GalleryAdults.LANGUAGE_RUSSIAN),
        IMHentai("all", GalleryAdults.LANGUAGE_MULTI),
    )
}
