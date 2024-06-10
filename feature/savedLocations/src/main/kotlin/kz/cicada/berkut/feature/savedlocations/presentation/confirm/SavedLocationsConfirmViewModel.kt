package kz.cicada.berkut.feature.savedlocations.presentation.confirm

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kz.cicada.berkut.feature.savedlocations.domain.repository.SavedLocationsRepository
import kz.cicada.berkut.lib.core.data.local.UserPreferences
import kz.cicada.berkut.lib.core.ui.base.BaseViewModel
import kz.cicada.berkut.lib.core.ui.compose.extension.tryToUpdate
import kz.cicada.berkut.lib.core.ui.event.ActionResultEvent
import kz.cicada.berkut.lib.core.ui.event.CloseScreenEvent

class SavedLocationsConfirmViewModel(
    private val launcher: SavedLocationsConfirmLauncher,
    private val userPref: UserPreferences,
    private val repo: SavedLocationsRepository,
) : BaseViewModel(), SavedLocationsConfirmController {
    val uiState = MutableStateFlow(
        SavedLocationsConfirmUIState.Data(
            name = "",
            notify = true,
        )
    )

    override fun requestSaveLocation() {
        val data = uiState.value
        val name = data.name
        val notify = data.notify

        networkRequest(
            request = {
                val id = userPref.getId().first().toInt()
                repo.saveLocations(
                    parentId = id,
                    latitude = launcher.latitude,
                    longitude = launcher.longitude,
                    name = name,
                    radius = launcher.radius,
                    notify = notify,
                    address = launcher.address,
                )
            },
            finally = {
                sendEvent(ActionResultEvent(SavedLocationsConfirmResultEvent))
            },
        )
    }

    override fun navigateUp() {
        sendEvent(CloseScreenEvent)
    }

    override fun onNameTextChanged(text: String) {
        uiState.tryToUpdate {
            SavedLocationsConfirmUIState.Data(
                notify = true,
                name = text,
            )
        }
    }
}