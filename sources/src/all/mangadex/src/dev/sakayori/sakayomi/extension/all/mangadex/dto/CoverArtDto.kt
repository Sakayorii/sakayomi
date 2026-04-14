package dev.sakayori.sakayomi.extension.all.mangadex.dto

import dev.sakayori.sakayomi.extension.all.mangadex.MDConstants
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

typealias CoverArtListDto = PaginatedResponseDto<CoverArtDto>

@Serializable
@SerialName(MDConstants.COVER_ART)
data class CoverArtDto(override val attributes: CoverArtAttributesDto? = null) : EntityDto()

@Serializable
data class CoverArtAttributesDto(
    val fileName: String? = null,
    val locale: String? = null,
) : AttributesDto()
