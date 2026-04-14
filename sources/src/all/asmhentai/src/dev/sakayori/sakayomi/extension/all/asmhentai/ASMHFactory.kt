package dev.sakayori.sakayomi.extension.all.asmhentai

import dev.sakayori.sakayomi.multisrc.galleryadults.GalleryAdults
import dev.sakayori.sakayomi.source.Source
import dev.sakayori.sakayomi.source.SourceFactory

class ASMHFactory : SourceFactory {
    override fun createSources(): List<Source> = listOf(
        AsmHentai("en", GalleryAdults.LANGUAGE_ENGLISH),
        AsmHentai("ja", GalleryAdults.LANGUAGE_JAPANESE),
        AsmHentai("zh", GalleryAdults.LANGUAGE_CHINESE),
        AsmHentai("all", GalleryAdults.LANGUAGE_MULTI),
    )
}
