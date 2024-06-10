package kz.cicada.berkut.feature.children.presentation.details

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import kz.cicada.berkut.lib.core.ui.compose.base.ComposeBottomSheetFragment
import kz.cicada.berkut.lib.core.ui.navigation.FragmentTransition
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class ChildDetailsBottomSheetFragment(
    private val launcher: ChildDetailsLauncher,
) : ComposeBottomSheetFragment(), FragmentTransition.BottomTop {
    override val viewModel: ChildDetailsViewModel by viewModel(parameters = { parametersOf(launcher) })

    @Composable
    override fun Content() {
        val uiState by viewModel.uiState.collectAsState()
        ChildDetailsContent(
            uiState = uiState,
            controller = viewModel,
        )
    }
}