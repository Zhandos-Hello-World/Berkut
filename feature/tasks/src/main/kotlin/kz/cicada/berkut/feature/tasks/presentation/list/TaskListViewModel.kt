package kz.cicada.berkut.feature.tasks.presentation.list

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kz.cicada.berkut.feature.tasks.domain.model.Task
import kz.cicada.berkut.feature.tasks.domain.model.TaskStatus
import kz.cicada.berkut.feature.tasks.domain.repository.TaskRepository
import kz.cicada.berkut.feature.tasks.navigation.TasksScreen
import kz.cicada.berkut.feature.tasks.presentation.add.AddTaskLauncher
import kz.cicada.berkut.feature.tasks.presentation.details.TaskDetailsLauncher
import kz.cicada.berkut.lib.core.data.local.UserPreferences
import kz.cicada.berkut.lib.core.data.network.UserType
import kz.cicada.berkut.lib.core.ui.base.BaseViewModel
import kz.cicada.berkut.lib.core.ui.base.LoadingType
import kz.cicada.berkut.lib.core.ui.base.ViewState
import kz.cicada.berkut.lib.core.ui.compose.widgets.expandable.SelectedListItem
import kz.cicada.berkut.lib.core.ui.event.CloseScreenEvent
import kz.cicada.berkut.lib.core.ui.event.OpenScreenEvent
import kz.cicada.berkut.lib.core.ui.extensions.dataOrNull
import kz.cicada.berkut.lib.core.ui.extensions.tryToUpdate

class TaskListViewModel(
    private val launcher: TaskListLauncher,
    private val repository: TaskRepository,
    private val preferences: UserPreferences,
) : BaseViewModel(), TaskListController {
    val uiState =
        MutableStateFlow<ViewState<TaskListDataState>>(ViewState.Loading(LoadingType.Progress))
    private var cachedTasks = mutableListOf<Task>()

    init {
        getTasks()
    }

    override fun navigateUp() = sendEvent(CloseScreenEvent)

    override fun onDeleteTask(model: SelectedListItem.SelectedItem) {
        networkRequest(
            request = {
                repository.deleteTask(
                    taskId = model.id,
                    childId = launcher.childId,
                )
            },
        )
        uiState.tryToUpdate {
            val state = (uiState.value as ViewState.Data)
            state.data.list.forEach {
                it.items.remove(model)
            }
            state
        }
    }

    override fun onTaskClick(model: SelectedListItem.SelectedItem) {
        val selectedTask = cachedTasks.firstOrNull { it.id == model.id } ?: return
        sendEvent(
            OpenScreenEvent(
                TasksScreen.TaskDetails(
                    launcher = TaskDetailsLauncher(
                        taskId = selectedTask.id,
                        taskName = selectedTask.name,
                        taskDescription = selectedTask.description,
                        status = selectedTask.status,
                        coins = selectedTask.coins,
                    )
                )
            )
        )
    }

    override fun onMoveClick(model: SelectedListItem.SelectedItem) {
        val state = uiState.value.dataOrNull ?: return
        var foundSelect: SelectedListItem.SelectedItem? = null
        var lastItem: SelectedListItem? = null

        for (item in state.list) {
            if (foundSelect != null) {
                lastItem?.items?.remove(foundSelect)
                foundSelect.let(item.items::add)

                changeStatusOfTheTask(
                    taskId = foundSelect.id,
                    status = TaskStatus.find(item.selector),
                )
                break
            }
            lastItem = item
            foundSelect = item.items.firstOrNull { it.id == model.id }
        }
        uiState.tryToUpdate {
            ViewState.Data(
                state,
            )
        }
    }

    override fun onAddTaskClick() {
        sendEvent(
            OpenScreenEvent(
                TasksScreen.AddTask(
                    launcher = AddTaskLauncher(childId = launcher.childId),
                ),
            ),
        )
    }

    private fun getTasks() {
        networkRequest(
            request = { repository.getTasks(launcher.childId) },
            onSuccess = { response ->
                val userType = preferences.getType().first()
                cachedTasks = response.toMutableList()
                val new = SelectedListItem(
                    selector = TaskStatus.NEW.name,
                    movable = true,
                    items = cachedTasks.filter { it.status == TaskStatus.NEW }
                        .map(::mapToSelectedListItem).toMutableList(),
                )
                val inVerification = SelectedListItem(
                    selector = TaskStatus.VERIFIED.name,
                    movable = userType == UserType.PARENT.name,
                    cachedTasks.filter { it.status == TaskStatus.VERIFIED }
                        .map(::mapToSelectedListItem).toMutableList(),
                )
                val done = SelectedListItem(
                    selector = TaskStatus.CONFIRMED.name,
                    movable = userType == UserType.PARENT.name,
                    cachedTasks.filter { it.status == TaskStatus.CONFIRMED }
                        .map(::mapToSelectedListItem).toMutableList(),
                )
                uiState.tryToUpdate {
                    ViewState.Data(
                        TaskListDataState(
                            list = mutableListOf(
                                new,
                                inVerification,
                                done,
                            ),
                            isParent = userType == UserType.PARENT.name,
                        )
                    )
                }
            },
            onError = {
                it.printStackTrace()
            }
        )
    }

    private fun changeStatusOfTheTask(
        taskId: Int,
        status: TaskStatus,
    ) {
        networkRequest(
            request = {
                repository.changeStatus(
                    taskId = taskId, status = status
                )
            },
        )
    }

    private fun mapToSelectedListItem(task: Task): SelectedListItem.SelectedItem {
        return SelectedListItem.SelectedItem(
            id = task.id,
            name = task.name,
            coins = task.coins,
        )
    }
}