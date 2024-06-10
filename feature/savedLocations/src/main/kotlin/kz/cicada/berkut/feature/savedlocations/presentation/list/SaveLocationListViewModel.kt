package kz.cicada.berkut.feature.savedlocations.presentation.list

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kz.cicada.berkut.feature.savedlocations.data.model.SavedLocationResponse
import kz.cicada.berkut.feature.savedlocations.domain.repository.SavedLocationsRepository
import kz.cicada.berkut.feature.savedlocations.navigation.SavedLocationsScreens
import kz.cicada.berkut.feature.savedlocations.presentation.maps.SavedLocationsMapLauncher
import kz.cicada.berkut.lib.core.data.local.UserPreferences
import kz.cicada.berkut.lib.core.data.network.UserType
import kz.cicada.berkut.lib.core.ui.base.BaseViewModel
import kz.cicada.berkut.lib.core.ui.base.ViewState
import kz.cicada.berkut.lib.core.ui.event.CloseScreenEvent
import kz.cicada.berkut.lib.core.ui.event.OpenScreenEvent
import kz.cicada.berkut.lib.core.ui.extensions.tryToUpdate

class SaveLocationListViewModel(
    private val repository: SavedLocationsRepository,
    private val userPreferences: UserPreferences,
) : BaseViewModel(), SaveLocationListController {
    val uiState = MutableStateFlow<SaveLocationListUIState>(
        SaveLocationListUIState.Loading,
    )
    private var savedLocations: MutableList<SavedLocationResponse> = mutableListOf()

    init {
        getData()
    }

    private fun getData() {
        networkRequest(
            request = {
                repository.getSaveLocations(childId = userPreferences.getId().first().toInt())
            },
            onSuccess = { response ->
                val isParent = userPreferences.getType().first() == UserType.PARENT.name
                savedLocations = response.toMutableList()
                uiState.tryToUpdate {
                    SaveLocationListUIState.Data(
                        savedLocations,
                        isParent = isParent,
                    )
                }
            },
        )
    }

    override fun navigateUp() {
        sendEvent(CloseScreenEvent)
    }

    override fun onAddSaveLocationClick() {
        sendEvent(
            OpenScreenEvent(
                SavedLocationsScreens.SavedLocationsMap(
                    SavedLocationsMapLauncher(
                        list = savedLocations.map {
                            SavedLocationsMapLauncher.SafeLocation(
                                name = it.name,
                                radius = it.radius,
                                latitude = it.latitude,
                                longitude = it.longitude,
                            )
                        },
                    ),
                ),
            ),
        )
    }

    override fun onDeleteClick(savedLocation: SavedLocationResponse) {
        networkRequest(
            request = {
                repository.deleteLocation(
                    userId = userPreferences.getId().first().toInt(),
                    locationId = savedLocation.id
                )
            },
        )
        uiState.tryToUpdate {
            val state = (uiState.value as SaveLocationListUIState.Data)
            state.list.remove(savedLocation)
            state
        }
    }
}