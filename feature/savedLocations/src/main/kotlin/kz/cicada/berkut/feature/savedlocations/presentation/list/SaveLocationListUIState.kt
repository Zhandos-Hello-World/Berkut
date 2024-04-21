package kz.cicada.berkut.feature.savedlocations.presentation.list

import kz.cicada.berkut.feature.savedlocations.data.model.SavedLocationResponse

sealed interface SaveLocationListUIState {
    data class Data(
        val list: List<SavedLocationResponse>,
        val isParent: Boolean,
    ) : SaveLocationListUIState

    data object Loading: SaveLocationListUIState
}