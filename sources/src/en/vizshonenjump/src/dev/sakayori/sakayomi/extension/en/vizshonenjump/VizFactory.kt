package dev.sakayori.sakayomi.extension.en.vizshonenjump

import dev.sakayori.sakayomi.source.SourceFactory

class VizFactory : SourceFactory {
    override fun createSources() = listOf(
        Viz("VIZ Shonen Jump", "shonenjump"),
        Viz("VIZ Manga", "vizmanga"),
    )
}
