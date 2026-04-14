package dev.sakayori.sakayomi.extension.fr.lesporoiniens

import dev.sakayori.sakayomi.multisrc.scanr.ChapterData
import kotlinx.serialization.Serializable
import kotlinx.serialization.builtins.MapSerializer
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonTransformingSerializer

@Serializable
class LocalReaderData(
    val series: LocalSeriesData,
)

@Serializable
class LocalSeriesData(
    @Serializable(with = ChaptersAsMapSerializer::class)
    val chapters: Map<String, ChapterData>? = null,
)

object ChaptersAsMapSerializer : JsonTransformingSerializer<Map<String, ChapterData>>(
    MapSerializer(String.serializer(), ChapterData.serializer()),
) {
    override fun transformDeserialize(element: JsonElement): JsonElement {
        if (element is JsonArray) {
            return JsonObject(
                element.mapIndexed { index, item ->
                    (index + 1).toString() to item
                }.toMap(),
            )
        }
        return element
    }
}
