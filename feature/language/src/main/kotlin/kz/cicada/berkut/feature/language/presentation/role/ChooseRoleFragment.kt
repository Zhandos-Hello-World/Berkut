package kz.cicada.berkut.feature.language.presentation.role

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import kz.cicada.berkut.lib.core.ui.compose.base.ComposeFragment
import kz.cicada.berkut.lib.core.ui.compose.extension.collectAsStateWithLifecycle
import kz.cicada.berkut.lib.core.ui.navigation.FragmentTransition
import org.koin.androidx.viewmodel.ext.android.viewModel

class ChooseRoleFragment : ComposeFragment(), FragmentTransition.LeftRight {
    override val viewModel: ChooseRoleViewModel by viewModel()

    @Composable
    override fun Content() {
        val uiState by viewModel.uiState.collectAsStateWithLifecycle()
        ChooseRoleContent(
            uiState = uiState,
            controller = viewModel,
        )
    }
}