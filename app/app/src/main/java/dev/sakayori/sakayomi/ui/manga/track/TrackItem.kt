package dev.sakayori.sakayomi.ui.manga.track

import dev.sakayori.sakayomi.data.track.Tracker
import tachiyomi.domain.track.model.Track

data class TrackItem(val track: Track?, val tracker: Tracker)
