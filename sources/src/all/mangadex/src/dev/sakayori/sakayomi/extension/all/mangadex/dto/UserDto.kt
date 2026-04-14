package dev.sakayori.sakayomi.extension.all.mangadex.dto

import dev.sakayori.sakayomi.extension.all.mangadex.MDConstants
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(MDConstants.USER)
data class UserDto(override val attributes: UserAttributes? = null) : EntityDto()

@Serializable
data class UserAttributes(val username: String) : AttributesDto()
