package dev.sakayori.sakayomi.extension.ja.comicmeteor

import kotlinx.serialization.Serializable

@Serializable
class ApiTitlesResponse(
    val data: List<ApiTitle>,
)

@Serializable
class ApiTitle(
    val name: String,
    val url: String,
    val thumbnail: String,
)
