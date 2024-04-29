package kz.cicada.berkut.feature.profile.presentation.profile

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import kz.cicada.berkut.lib.core.ui.compose.base.ComposeFragment
import kz.cicada.berkut.lib.core.ui.compose.widgets.progress.CustomProgressBar
import kz.cicada.berkut.lib.core.ui.navigation.FragmentTransition
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProfileFragment : ComposeFragment(), FragmentTransition.LeftRight {
    override val viewModel: ProfileViewModel by viewModel()

    @Composable
    override fun Content() {
        val uiState by viewModel.uiState.collectAsState()
        when (val state = uiState) {
            is ProfIleUIState.Data -> ProfileContent(
                controller = viewModel,
                uiState = state,
            )
            ProfIleUIState.Loading -> CustomProgressBar()
        }
    }
}