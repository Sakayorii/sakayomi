package dev.sakayori.presentation.browse

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import dev.sakayori.presentation.components.AppBar
import dev.sakayori.presentation.more.settings.widget.SwitchPreferenceWidget
import dev.sakayori.sakayomi.ui.browse.extension.ExtensionFilterState
import dev.sakayori.sakayomi.util.system.LocaleHelper
import tachiyomi.i18n.MR
import tachiyomi.presentation.core.components.material.Scaffold
import tachiyomi.presentation.core.i18n.stringResource
import tachiyomi.presentation.core.screens.EmptyScreen

@Composable
fun ExtensionFilterScreen(
    navigateUp: () -> Unit,
    state: ExtensionFilterState.Success,
    onClickToggle: (String) -> Unit,
) {
    Scaffold(
        topBar = { scrollBehavior ->
            AppBar(
                title = stringResource(MR.strings.label_extensions),
                navigateUp = navigateUp,
                scrollBehavior = scrollBehavior,
            )
        },
    ) { contentPadding ->
        if (state.isEmpty) {
            EmptyScreen(
                stringRes = MR.strings.empty_screen,
                modifier = Modifier.padding(contentPadding),
            )
            return@Scaffold
        }
        ExtensionFilterContent(
            contentPadding = contentPadding,
            state = state,
            onClickLang = onClickToggle,
        )
    }
}

@Composable
private fun ExtensionFilterContent(
    contentPadding: PaddingValues,
    state: ExtensionFilterState.Success,
    onClickLang: (String) -> Unit,
) {
    val context = LocalContext.current
    LazyColumn(
        contentPadding = contentPadding,
    ) {
        items(state.languages) { language ->
            SwitchPreferenceWidget(
                modifier = Modifier.animateItem(),
                title = LocaleHelper.getSourceDisplayName(language, context),
                checked = language in state.enabledLanguages,
                onCheckedChanged = { onClickLang(language) },
            )
        }
    }
}
