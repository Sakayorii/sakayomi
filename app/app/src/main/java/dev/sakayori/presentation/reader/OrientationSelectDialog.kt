package dev.sakayori.presentation.reader

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import dev.icerock.moko.resources.StringResource
import dev.sakayori.domain.manga.model.readerOrientation
import dev.sakayori.presentation.components.AdaptiveSheet
import dev.sakayori.presentation.reader.components.ModeSelectionDialog
import dev.sakayori.presentation.theme.TachiyomiPreviewTheme
import dev.sakayori.sakayomi.ui.reader.setting.ReaderOrientation
import dev.sakayori.sakayomi.ui.reader.setting.ReaderSettingsScreenModel
import tachiyomi.i18n.MR
import tachiyomi.presentation.core.components.SettingsIconGrid
import tachiyomi.presentation.core.components.material.IconToggleButton
import tachiyomi.presentation.core.i18n.stringResource

private val ReaderOrientationsWithoutDefault = ReaderOrientation.entries - ReaderOrientation.DEFAULT

@Composable
fun OrientationSelectDialog(
    onDismissRequest: () -> Unit,
    screenModel: ReaderSettingsScreenModel,
    onChange: (StringResource) -> Unit,
) {
    val manga by screenModel.mangaFlow.collectAsState()
    val orientation = remember(manga) { ReaderOrientation.fromPreference(manga?.readerOrientation?.toInt()) }

    AdaptiveSheet(onDismissRequest = onDismissRequest) {
        DialogContent(
            orientation = orientation,
            onChangeOrientation = {
                screenModel.onChangeOrientation(it)
                onChange(it.stringRes)
                onDismissRequest()
            },
        )
    }
}

@Composable
private fun DialogContent(
    orientation: ReaderOrientation,
    onChangeOrientation: (ReaderOrientation) -> Unit,
) {
    var selected by remember { mutableStateOf(orientation) }

    ModeSelectionDialog(
        onUseDefault = {
            onChangeOrientation(
                ReaderOrientation.DEFAULT,
            )
        }.takeIf { orientation != ReaderOrientation.DEFAULT },
        onApply = { onChangeOrientation(selected) },
    ) {
        SettingsIconGrid(MR.strings.rotation_type) {
            items(ReaderOrientationsWithoutDefault) { mode ->
                IconToggleButton(
                    checked = mode == selected,
                    onCheckedChange = {
                        selected = mode
                    },
                    modifier = Modifier.fillMaxWidth(),
                    imageVector = mode.icon,
                    title = stringResource(mode.stringRes),
                )
            }
        }
    }
}

@PreviewLightDark
@Composable
private fun DialogContentPreview() {
    TachiyomiPreviewTheme {
        Surface {
            Column {
                DialogContent(
                    orientation = ReaderOrientation.DEFAULT,
                    onChangeOrientation = {},
                )

                DialogContent(
                    orientation = ReaderOrientation.FREE,
                    onChangeOrientation = {},
                )
            }
        }
    }
}
