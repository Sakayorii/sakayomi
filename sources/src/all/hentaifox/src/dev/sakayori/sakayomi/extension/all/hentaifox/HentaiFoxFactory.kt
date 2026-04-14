package dev.sakayori.sakayomi.extension.all.hentaifox

import dev.sakayori.sakayomi.multisrc.galleryadults.GalleryAdults
import dev.sakayori.sakayomi.source.Source
import dev.sakayori.sakayomi.source.SourceFactory

class HentaiFoxFactory : SourceFactory {
    override fun createSources(): List<Source> = listOf(
        HentaiFox("en", GalleryAdults.LANGUAGE_ENGLISH),
        HentaiFox("ja", GalleryAdults.LANGUAGE_JAPANESE),
        HentaiFox("zh", GalleryAdults.LANGUAGE_CHINESE),
        HentaiFox("ko", GalleryAdults.LANGUAGE_KOREAN),
        HentaiFox("all", GalleryAdults.LANGUAGE_MULTI),
    )
}
