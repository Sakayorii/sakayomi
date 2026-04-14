package dev.sakayori.sakayomi.extension.vi.vcomycs

import kotlinx.serialization.Serializable

@Serializable
class SearchResponse(
    val data: List<SearchResultDto> = emptyList(),
)

@Serializable
class SearchResultDto(
    val title: String,
    val link: String,
    val img: String? = null,
    val star: Double? = null,
    val vote: Int? = null,
    val cstatus: String? = null,
)
