package dev.sakayori.sakayomi.extension.en.manhwaread

import kotlinx.serialization.Serializable

@Serializable
class ChapterData(
    val data: String,
    val base: String,
)

@Serializable
class ChapterDataData(
    val src: String,
)
