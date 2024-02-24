package am.strongte.hub.auth.presentation.input.email

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import kz.cicada.berkut.lib.core.ui.compose.base.ComposeFragment
import kz.cicada.berkut.lib.core.ui.compose.extension.collectAsStateWithLifecycle
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

internal class InputEmailFragment(
    private val launcher: InputEmailLauncher,
): ComposeFragment() {
    override val viewModel: InputEmailViewModel by viewModel(parameters = { parametersOf(launcher) })

    @Composable
    override fun Content() {
        val uiState by viewModel.uiState.collectAsStateWithLifecycle()
        InputEmailContent(
            uiState = uiState,
            controller = viewModel,
        )
    }
}