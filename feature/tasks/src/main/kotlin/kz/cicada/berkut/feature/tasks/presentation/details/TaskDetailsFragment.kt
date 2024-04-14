package kz.cicada.berkut.feature.tasks.presentation.details

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import kz.cicada.berkut.lib.core.ui.compose.base.ComposeFragment
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class TaskDetailsFragment(
    private val launcher: TaskDetailsLauncher,
) : ComposeFragment() {
    override val viewModel: TaskDetailsViewModel by viewModel(
        parameters = {
            parametersOf(launcher)
        },
    )

    @Composable
    override fun Content() {
        val uiState by viewModel.uiState.collectAsState()
        TaskDetailsContent(
            controller = viewModel,
            uiState = uiState,
        )
    }
}