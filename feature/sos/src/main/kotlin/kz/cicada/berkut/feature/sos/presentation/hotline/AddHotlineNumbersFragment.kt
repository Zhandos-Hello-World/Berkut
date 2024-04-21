package kz.cicada.berkut.feature.sos.presentation.hotline

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import kz.cicada.berkut.lib.core.ui.compose.base.ComposeFragment
import kz.cicada.berkut.lib.core.ui.navigation.FragmentTransition
import org.koin.androidx.viewmodel.ext.android.viewModel

class AddHotlineNumbersFragment : ComposeFragment(), FragmentTransition.LeftRight {
    override val viewModel: AddHotlineNumbersViewModel by viewModel()

    @Composable
    override fun Content() {
        val uiState by viewModel.state.collectAsState()
        AddHotlineNumbersContent(
            uiState = uiState,
            controller = viewModel,
        )
    }
}