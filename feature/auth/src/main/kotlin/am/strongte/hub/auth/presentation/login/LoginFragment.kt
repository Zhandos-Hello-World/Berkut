package am.strongte.hub.auth.presentation.login

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import kz.cicada.berkut.lib.core.ui.compose.base.ComposeFragment
import kz.cicada.berkut.lib.core.ui.compose.extension.collectAsStateWithLifecycle
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginFragment : ComposeFragment() {
    override val viewModel: LoginViewModel by viewModel()

    @Composable
    override fun Content() {
        val uiState by viewModel.uiState.collectAsStateWithLifecycle()
        LoginContent(
            uiState = uiState,
            controller = viewModel,
        )
    }
}