package kz.cicada.berkut.feature.tasks.presentation.add

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import kz.cicada.berkut.lib.core.ui.compose.base.ComposeBottomSheetFragment
import kz.cicada.berkut.lib.core.ui.compose.base.ComposeFragment
import kz.cicada.berkut.lib.core.ui.navigation.FragmentTransition
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class AddTaskFragment(
    private val launcher: AddTaskLauncher,
) : ComposeBottomSheetFragment(), FragmentTransition.LeftRight {
    override val viewModel: AddTaskViewModel by viewModel(parameters = { parametersOf(launcher) })

    @Composable
    override fun Content() {
        val uiState by viewModel.uiState.collectAsState()
        AddTaskContent(
            controller = viewModel,
            uiState = uiState,
        )
    }
}