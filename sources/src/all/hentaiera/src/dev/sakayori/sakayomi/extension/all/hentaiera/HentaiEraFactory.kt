package dev.sakayori.sakayomi.extension.all.hentaiera

import dev.sakayori.sakayomi.multisrc.galleryadults.GalleryAdults
import dev.sakayori.sakayomi.source.Source
import dev.sakayori.sakayomi.source.SourceFactory

class HentaiEraFactory : SourceFactory {

    override fun createSources(): List<Source> = listOf(
        HentaiEra("en", GalleryAdults.LANGUAGE_ENGLISH),
        HentaiEra("ja", GalleryAdults.LANGUAGE_JAPANESE),
        HentaiEra("es", GalleryAdults.LANGUAGE_SPANISH),
        HentaiEra("fr", GalleryAdults.LANGUAGE_FRENCH),
        HentaiEra("ko", GalleryAdults.LANGUAGE_KOREAN),
        HentaiEra("de", GalleryAdults.LANGUAGE_GERMAN),
        HentaiEra("ru", GalleryAdults.LANGUAGE_RUSSIAN),
        HentaiEra("all", GalleryAdults.LANGUAGE_MULTI),
    )
}
