package kz.cicada.berkut.feature.tasks.presentation.add

import kz.cicada.berkut.lib.core.empty

sealed interface AddTaskUIState {

    data class Data(
        val name: String = String.empty,
        val description: String = String.empty,
        val coins: String = String.empty,
    )
}