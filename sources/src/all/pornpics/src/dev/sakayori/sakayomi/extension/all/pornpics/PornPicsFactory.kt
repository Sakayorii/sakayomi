package dev.sakayori.sakayomi.extension.all.pornpics

import dev.sakayori.sakayomi.source.SourceFactory

class PornPicsFactory : SourceFactory {

    override fun createSources() = listOf(
        PornPics("en"),
        PornPics("zh"),
    )
}
