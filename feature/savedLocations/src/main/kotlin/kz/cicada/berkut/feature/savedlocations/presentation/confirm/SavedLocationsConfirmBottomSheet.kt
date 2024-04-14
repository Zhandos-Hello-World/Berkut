package kz.cicada.berkut.feature.savedlocations.presentation.confirm

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import kz.cicada.berkut.lib.core.ui.compose.base.ComposeBottomSheetFragment
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class SavedLocationsConfirmBottomSheet(
    private val launcher: SavedLocationsConfirmLauncher,
) : ComposeBottomSheetFragment() {
    override val viewModel: SavedLocationsConfirmViewModel by viewModel(
        parameters = {
            parametersOf(
                launcher
            )
        },
    )

    @Composable
    override fun Content() {
        val uiState by viewModel.uiState.collectAsState()
        SavedLocationContent(
            controller = viewModel,
            uiState = uiState,
        )
    }
}