package kz.cicada.berkut.feature.tasks.presentation.details

import kotlinx.coroutines.flow.MutableStateFlow
import kz.cicada.berkut.feature.tasks.domain.repository.TaskRepository
import kz.cicada.berkut.lib.core.ui.base.BaseViewModel
import kz.cicada.berkut.lib.core.ui.base.ViewState
import kz.cicada.berkut.lib.core.ui.event.CloseScreenEvent

class TaskDetailsViewModel(
    launcher: TaskDetailsLauncher,
    private val repository: TaskRepository,
) : BaseViewModel(), TaskDetailsController {
    val uiState = MutableStateFlow<ViewState<TaskDetailsDataState>>(
        ViewState.Data(
            data = TaskDetailsDataState(
                taskName = launcher.taskName,
                taskDescription = launcher.taskDescription,
                coins = launcher.coins,
            ),
        )
    )

    override fun onNavigateBack() = sendEvent(CloseScreenEvent)

    override fun onDoneClick() {
        networkRequest(
            request = {
                repository
            },
        )
    }
}