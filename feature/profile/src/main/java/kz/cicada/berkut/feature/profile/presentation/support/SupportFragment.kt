package kz.cicada.berkut.feature.profile.presentation.support

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import kz.cicada.berkut.lib.core.ui.compose.base.ComposeFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class SupportFragment : ComposeFragment() {
    override val viewModel: SupportViewModel by viewModel()

    @Composable
    override fun Content() {
        val uiState by viewModel.uiState.collectAsState()
        SupportContent(
            uiState = uiState,
            controller = viewModel,
        )
    }
}