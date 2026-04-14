package dev.sakayori.sakayomi.data.track.kitsu

import dev.sakayori.sakayomi.data.database.models.Track

fun Track.toApiStatus() = when (status) {
    Kitsu.READING -> "current"
    Kitsu.COMPLETED -> "completed"
    Kitsu.ON_HOLD -> "on_hold"
    Kitsu.DROPPED -> "dropped"
    Kitsu.PLAN_TO_READ -> "planned"
    else -> throw Exception("Unknown status")
}

fun Track.toApiScore(): String? {
    return if (score > 0) (score * 2).toInt().toString() else null
}
