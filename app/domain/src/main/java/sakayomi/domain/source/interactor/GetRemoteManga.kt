package tachiyomi.domain.source.interactor

import dev.sakayori.sakayomi.source.model.FilterList
import tachiyomi.domain.source.repository.SourcePagingSource
import tachiyomi.domain.source.repository.SourceRepository

class GetRemoteManga(
    private val repository: SourceRepository,
) {

    operator fun invoke(sourceId: Long, query: String, filterList: FilterList): SourcePagingSource {
        return when (query) {
            QUERY_POPULAR -> repository.getPopular(sourceId)
            QUERY_LATEST -> repository.getLatest(sourceId)
            else -> repository.search(sourceId, query, filterList)
        }
    }

    companion object {
        const val QUERY_POPULAR = "dev.sakayori.domain.source.interactor.POPULAR"
        const val QUERY_LATEST = "dev.sakayori.domain.source.interactor.LATEST"
    }
}
