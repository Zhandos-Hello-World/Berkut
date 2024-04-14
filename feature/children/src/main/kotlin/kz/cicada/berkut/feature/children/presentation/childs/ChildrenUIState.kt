package kz.cicada.berkut.feature.children.presentation.childs

import kz.cicada.berkut.feature.children.data.model.ChildrenResponse

sealed interface ChildrenUIState {
    data object Loading : ChildrenUIState

    data class Data(
        val list: List<ChildrenResponse>,
    ) : ChildrenUIState
}