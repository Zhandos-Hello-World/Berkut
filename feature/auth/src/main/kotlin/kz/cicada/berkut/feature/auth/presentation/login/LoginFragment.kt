package kz.cicada.berkut.feature.auth.presentation.login

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import kz.cicada.berkut.lib.core.ui.compose.base.ComposeFragment
import kz.cicada.berkut.lib.core.ui.compose.extension.collectAsStateWithLifecycle
import kz.cicada.berkut.lib.core.ui.navigation.FragmentTransition
import org.koin.androidx.viewmodel.ext.android.viewModel

internal class LoginFragment : ComposeFragment(), FragmentTransition.LeftRight {
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