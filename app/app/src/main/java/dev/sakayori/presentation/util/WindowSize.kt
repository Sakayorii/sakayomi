package dev.sakayori.presentation.util

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.platform.LocalConfiguration
import dev.sakayori.sakayomi.util.system.isTabletUi

@Composable
@ReadOnlyComposable
fun isTabletUi(): Boolean {
    return LocalConfiguration.current.isTabletUi()
}
