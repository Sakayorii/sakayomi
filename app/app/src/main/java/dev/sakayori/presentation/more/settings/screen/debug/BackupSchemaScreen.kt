package dev.sakayori.presentation.more.settings.screen.debug

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import dev.sakayori.presentation.components.AppBar
import dev.sakayori.presentation.components.AppBarActions
import dev.sakayori.presentation.util.Screen
import dev.sakayori.sakayomi.data.backup.models.Backup
import dev.sakayori.sakayomi.util.system.copyToClipboard
import kotlinx.collections.immutable.persistentListOf
import kotlinx.serialization.protobuf.schema.ProtoBufSchemaGenerator
import tachiyomi.i18n.MR
import tachiyomi.presentation.core.components.material.Scaffold
import tachiyomi.presentation.core.i18n.stringResource

class BackupSchemaScreen : Screen() {

    companion object {
        const val TITLE = "Backup file schema"
    }

    @Composable
    override fun Content() {
        val context = LocalContext.current
        val navigator = LocalNavigator.currentOrThrow

        val schema = remember { ProtoBufSchemaGenerator.generateSchemaText(Backup.serializer().descriptor) }

        Scaffold(
            topBar = {
                AppBar(
                    title = TITLE,
                    navigateUp = navigator::pop,
                    actions = {
                        AppBarActions(
                            persistentListOf(
                                AppBar.Action(
                                    title = stringResource(MR.strings.action_copy_to_clipboard),
                                    icon = Icons.Default.ContentCopy,
                                    onClick = {
                                        context.copyToClipboard(TITLE, schema)
                                    },
                                ),
                            ),
                        )
                    },
                    scrollBehavior = it,
                )
            },
        ) { contentPadding ->
            Text(
                text = schema,
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .padding(contentPadding)
                    .padding(16.dp),
                fontFamily = FontFamily.Monospace,
            )
        }
    }
}
