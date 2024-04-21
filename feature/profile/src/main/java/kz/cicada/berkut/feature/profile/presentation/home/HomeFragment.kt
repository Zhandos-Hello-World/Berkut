package kz.cicada.berkut.feature.profile.presentation.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import kz.cicada.berkut.lib.core.ui.compose.base.ComposeFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : ComposeFragment() {
    override val viewModel: HomeViewModel by viewModel()

    @Composable
    override fun Content() {
        val uiState by viewModel.state.collectAsState()
        HomeContent(
            controller = viewModel,
            state = uiState,
        )
    }
}