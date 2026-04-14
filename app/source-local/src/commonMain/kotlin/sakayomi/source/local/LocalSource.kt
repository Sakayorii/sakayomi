package tachiyomi.source.local

import dev.sakayori.sakayomi.source.CatalogueSource
import dev.sakayori.sakayomi.source.UnmeteredSource

expect class LocalSource : CatalogueSource, UnmeteredSource
