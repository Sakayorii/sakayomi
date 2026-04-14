package dev.sakayori.sakayomi.extension.ja.mangaparkpublisher

import kotlinx.serialization.Serializable

@Serializable
class ApiResponse(
    val data: ApiData,
)

@Serializable
class ApiData(
    val chapter: List<ApiPageData>,
)

@Serializable
class ApiPageData(
    val images: List<ApiImage>,
)

@Serializable
class ApiImage(
    val path: String,
    val key: String,
)
