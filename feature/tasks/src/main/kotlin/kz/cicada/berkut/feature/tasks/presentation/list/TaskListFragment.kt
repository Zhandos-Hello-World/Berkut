package kz.cicada.berkut.feature.tasks.presentation.list

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import kz.cicada.berkut.lib.core.ui.compose.base.ComposeFragment
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class TaskListFragment(
    private val launcher: TaskListLauncher,
) : ComposeFragment() {
    override val viewModel: TaskListViewModel by viewModel(parameters = { parametersOf(launcher) })

    @Composable
    override fun Content() {
        val uiState by viewModel.uiState.collectAsState()
        TaskListContent(
            controller = viewModel,
            uiState = uiState,
        )
    }
}