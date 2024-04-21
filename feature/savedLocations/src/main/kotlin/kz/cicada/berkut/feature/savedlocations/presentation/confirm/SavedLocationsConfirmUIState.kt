package kz.cicada.berkut.feature.savedlocations.presentation.confirm

sealed interface SavedLocationsConfirmUIState {
    data class Data(
        val notify: Boolean,
        val name: String,
    ) : SavedLocationsConfirmUIState
}