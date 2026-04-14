package dev.sakayori.sakayomi.ui.stats

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import dev.sakayori.presentation.components.AppBar
import dev.sakayori.presentation.more.stats.StatsScreenContent
import dev.sakayori.presentation.more.stats.StatsScreenState
import dev.sakayori.presentation.util.Screen
import tachiyomi.i18n.MR
import tachiyomi.presentation.core.components.material.Scaffold
import tachiyomi.presentation.core.i18n.stringResource
import tachiyomi.presentation.core.screens.LoadingScreen

class StatsScreen : Screen() {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow

        val screenModel = rememberScreenModel { StatsScreenModel() }
        val state by screenModel.state.collectAsState()

        Scaffold(
            topBar = { scrollBehavior ->
                AppBar(
                    title = stringResource(MR.strings.label_stats),
                    navigateUp = navigator::pop,
                    scrollBehavior = scrollBehavior,
                )
            },
        ) { paddingValues ->
            if (state is StatsScreenState.Loading) {
                LoadingScreen()
                return@Scaffold
            }

            StatsScreenContent(
                state = state as StatsScreenState.Success,
                paddingValues = paddingValues,
            )
        }
    }
}
