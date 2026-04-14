package dev.sakayori.sakayomi.extension.all.lanraragi

import dev.sakayori.sakayomi.source.Source
import dev.sakayori.sakayomi.source.SourceFactory

class LANraragiFactory : SourceFactory {
    override fun createSources(): List<Source> {
        val firstLrr = LANraragi("1")
        val lrrCount = firstLrr.preferences.getString(LANraragi.EXTRA_SOURCES_COUNT_KEY, LANraragi.EXTRA_SOURCES_COUNT_DEFAULT)!!.toInt()

        return buildList(lrrCount) {
            add(firstLrr)
            for (i in 2..lrrCount) {
                add(LANraragi("$i"))
            }
        }
    }
}
