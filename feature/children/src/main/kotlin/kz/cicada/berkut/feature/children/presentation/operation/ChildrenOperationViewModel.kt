package kz.cicada.berkut.feature.children.presentation.operation

import kotlinx.coroutines.flow.MutableStateFlow
import kz.cicada.berkut.feature.sos.presentation.navigation.HotlineNumberScreens
import kz.cicada.berkut.feature.tasks.navigation.TasksScreen
import kz.cicada.berkut.feature.tasks.presentation.list.TaskListLauncher
import kz.cicada.berkut.lib.core.ui.base.BaseViewModel
import kz.cicada.berkut.lib.core.ui.event.CloseScreenEvent
import kz.cicada.berkut.lib.core.ui.event.OpenScreenEvent

class ChildrenOperationViewModel(
    private val launcher: ChildrenOperationLauncher,
) : BaseViewModel(), ChildrenOperationController {
    val uiState = MutableStateFlow<ChildrenOperationUIState>(
        ChildrenOperationUIState.Data(
            name = launcher.username,
            imageUrl = launcher.imageUrl,
            phoneNumber = launcher.phoneNumber,
            coins = "342"
        )
    )

    override fun onNavigateBack() = sendEvent(CloseScreenEvent)

    override fun onTaskListClick() {
        sendEvent(
            OpenScreenEvent(
                screen = TasksScreen.TaskList(
                    launcher = TaskListLauncher(
                        childId = launcher.childrenId.toInt(),
                    )
                )
            ),
        )
    }

    override fun onAddHotlineClick() {
        sendEvent(
            OpenScreenEvent(
                screen = HotlineNumberScreens.AddHotlineNumbers()
            )
        )
    }
}