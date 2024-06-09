package kz.cicada.berkut.feature.savedlocations.presentation.list

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import kz.cicada.berkut.lib.core.ui.compose.base.ComposeFragment
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class SaveLocationListFragment: ComposeFragment() {
    override val viewModel: SaveLocationListViewModel by viewModel()

    @Composable
    override fun Content() {
        val uiState by viewModel.uiState.collectAsState()
        SaveLocationListContent(
            controller = viewModel,
            uiState = uiState,
        )
    }
}