package kz.cicada.berkut.feature.profile.presentation.hotline.listHotline

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import kz.cicada.berkut.lib.core.ui.compose.base.ComposeFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class HotlineListFragment : ComposeFragment() {
    override val viewModel: HotlineListViewModel by viewModel()

    @Composable
    override fun Content() {
        val state by viewModel.state.collectAsState()

        when (val uiState = state) {
            is HotlineListState.Data -> HotlineListContent(uiState)
            else -> {}
        }
    }
}