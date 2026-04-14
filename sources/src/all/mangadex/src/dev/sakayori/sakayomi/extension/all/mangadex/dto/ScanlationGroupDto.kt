package dev.sakayori.sakayomi.extension.all.mangadex.dto

import dev.sakayori.sakayomi.extension.all.mangadex.MDConstants
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(MDConstants.SCANLATION_GROUP)
data class ScanlationGroupDto(override val attributes: ScanlationGroupAttributes? = null) : EntityDto()

@Serializable
data class ScanlationGroupAttributes(val name: String) : AttributesDto()
