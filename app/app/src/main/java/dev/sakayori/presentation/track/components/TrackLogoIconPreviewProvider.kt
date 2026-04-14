package dev.sakayori.presentation.track.components

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import dev.sakayori.sakayomi.data.track.Tracker
import dev.sakayori.test.DummyTracker

internal class TrackLogoIconPreviewProvider : PreviewParameterProvider<Tracker> {

    override val values: Sequence<Tracker>
        get() = sequenceOf(
            DummyTracker(
                id = 1L,
                name = "Dummy Tracker",
            ),
        )
}
