package sakayomi.feature.migration.list.search

import dev.sakayori.sakayomi.source.CatalogueSource
import dev.sakayori.sakayomi.source.model.SManga
import sakayomi.domain.manga.model.toDomainManga
import tachiyomi.domain.manga.model.Manga

class SmartSourceSearchEngine(extraSearchParams: String?) : BaseSmartSearchEngine<SManga>(extraSearchParams) {

    override fun getTitle(result: SManga) = result.title

    suspend fun regularSearch(source: CatalogueSource, title: String): Manga? {
        return regularSearch(makeSearchAction(source), title).let {
            it?.toDomainManga(source.id)
        }
    }

    suspend fun deepSearch(source: CatalogueSource, title: String): Manga? {
        return deepSearch(makeSearchAction(source), title).let {
            it?.toDomainManga(source.id)
        }
    }

    private fun makeSearchAction(source: CatalogueSource): SearchAction<SManga> = { query ->
        source.getSearchManga(1, query, source.getFilterList()).mangas
    }
}
