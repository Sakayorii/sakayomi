package tachiyomi.domain.source.service

import dev.sakayori.sakayomi.source.CatalogueSource
import dev.sakayori.sakayomi.source.Source
import dev.sakayori.sakayomi.source.online.HttpSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import tachiyomi.domain.source.model.StubSource

interface SourceManager {

    val isInitialized: StateFlow<Boolean>

    val catalogueSources: Flow<List<CatalogueSource>>

    fun get(sourceKey: Long): Source?

    fun getOrStub(sourceKey: Long): Source

    fun getOnlineSources(): List<HttpSource>

    fun getCatalogueSources(): List<CatalogueSource>

    fun getStubSources(): List<StubSource>
}
