package am.strongte.hub.auth.presentation.input.email

import am.strongte.hub.auth.presentation.code.InputCodeContent
import am.strongte.hub.auth.presentation.code.InputCodeViewModel
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import kz.cicada.berkut.lib.core.ui.compose.base.ComposeFragment
import kz.cicada.berkut.lib.core.ui.compose.extension.collectAsStateWithLifecycle
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

internal class InputEmailFragment(
    private val launcher: InputEmailLauncher,
): ComposeFragment() {
    override val viewModel: InputCodeViewModel by viewModel(parameters = { parametersOf(launcher) })

    @Composable
    override fun Content() {
        val uiState by viewModel.uiState.collectAsStateWithLifecycle()
        InputCodeContent(
            uiState = uiState,
            controller = viewModel,
        )
    }
}