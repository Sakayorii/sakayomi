package dev.sakayori.sakayomi.extension.all.sandraandwoo

import dev.sakayori.sakayomi.extension.all.sandraandwoo.translations.SandraAndWooDE
import dev.sakayori.sakayomi.extension.all.sandraandwoo.translations.SandraAndWooEN
import dev.sakayori.sakayomi.source.SourceFactory

class SandraAndWooFactory : SourceFactory {
    override fun createSources() = listOf(
        SandraAndWooDE(),
        SandraAndWooEN(),
    )
}
