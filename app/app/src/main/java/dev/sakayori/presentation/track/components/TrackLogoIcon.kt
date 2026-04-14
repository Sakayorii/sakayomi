package dev.sakayori.presentation.track.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import dev.sakayori.presentation.theme.TachiyomiPreviewTheme
import dev.sakayori.sakayomi.data.track.Tracker
import tachiyomi.presentation.core.util.clickableNoIndication

@Composable
fun TrackLogoIcon(
    tracker: Tracker,
    onClick: (() -> Unit)? = null,
    onLongClick: (() -> Unit)? = null,
) {
    val modifier = if (onClick != null) {
        Modifier.clickableNoIndication(onClick = onClick, onLongClick = onLongClick)
    } else {
        Modifier
    }

    Image(
        painter = painterResource(tracker.getLogo()),
        contentDescription = tracker.name,
        modifier = modifier
            .size(48.dp)
            .clip(MaterialTheme.shapes.medium),
    )
}

@PreviewLightDark
@Composable
private fun TrackLogoIconPreviews(
    @PreviewParameter(TrackLogoIconPreviewProvider::class)
    tracker: Tracker,
) {
    TachiyomiPreviewTheme {
        TrackLogoIcon(
            tracker = tracker,
            onClick = null,
            onLongClick = null,
        )
    }
}
