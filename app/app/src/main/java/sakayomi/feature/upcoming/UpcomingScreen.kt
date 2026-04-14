package sakayomi.feature.upcoming

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import dev.sakayori.presentation.util.Screen
import dev.sakayori.sakayomi.ui.manga.MangaScreen

class UpcomingScreen : Screen() {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow

        val screenModel = rememberScreenModel { UpcomingScreenModel() }
        val state by screenModel.state.collectAsState()

        UpcomingScreenContent(
            state = state,
            setSelectedYearMonth = screenModel::setSelectedYearMonth,
            onClickUpcoming = { navigator.push(MangaScreen(it.id)) },
        )
    }
}
