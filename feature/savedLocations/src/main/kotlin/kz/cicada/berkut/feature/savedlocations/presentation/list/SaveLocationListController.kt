package kz.cicada.berkut.feature.savedlocations.presentation.list

import kz.cicada.berkut.feature.savedlocations.data.model.SavedLocationResponse

interface SaveLocationListController {
    fun navigateUp()
    fun onAddSaveLocationClick()
    fun onDeleteClick(savedLocation: SavedLocationResponse)

}