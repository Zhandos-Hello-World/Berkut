package kz.cicada.berkut.feature.savedlocations.presentation.confirm

interface SavedLocationsConfirmController {
    fun requestSaveLocation()
    fun navigateUp()
    fun onNameTextChanged(text: String)
}