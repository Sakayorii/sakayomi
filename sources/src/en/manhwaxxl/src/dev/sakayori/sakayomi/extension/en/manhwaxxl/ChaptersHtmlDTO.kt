package dev.sakayori.sakayomi.extension.en.manhwaxxl

import kotlinx.serialization.Serializable

@Serializable
class ChaptersHtmlDTO(
    val data: Data,
) {
    @Serializable
    class Data(
        val html: String,
    )
}
