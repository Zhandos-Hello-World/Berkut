package kz.cicada.berkut.feature.tasks.presentation.add

import kotlinx.coroutines.flow.MutableStateFlow
import kz.cicada.berkut.feature.tasks.domain.repository.TaskRepository
import kz.cicada.berkut.lib.core.ui.base.BaseViewModel
import kz.cicada.berkut.lib.core.ui.event.CloseScreenEvent
import kz.cicada.berkut.lib.core.ui.extensions.tryToUpdate

class AddTaskViewModel(
    private val launcher: AddTaskLauncher,
    private val repository: TaskRepository,
) : BaseViewModel(), AddTaskController {
    val uiState = MutableStateFlow(AddTaskUIState.Data())

    override fun onNavigateBack() = sendEvent(CloseScreenEvent)
    override fun inputCoins(coins: String) = uiState.tryToUpdate { it.copy(coins = coins) }
    override fun inputDescription(description: String) =
        uiState.tryToUpdate { it.copy(description = description) }

    override fun inputName(name: String) = uiState.tryToUpdate { it.copy(name = name) }

    override fun addTask() {
        val data = uiState.value
        if (!isValid(data)) {
            return
        }
        networkRequest(
            request = {
                repository.addTask(
                    name = data.name,
                    description = data.description,
                    coins = data.coins.toIntOrNull() ?: 0,
                    childId = launcher.childId,
                )
            },
            finally = {
                sendEvent(CloseScreenEvent)
            },
        )
    }

    private fun isValid(dataState: AddTaskUIState.Data): Boolean {
        return dataState.coins.trim().isNotEmpty() && dataState.coins.trim().isNotEmpty()
    }
}