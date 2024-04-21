package kz.cicada.berkut.feature.maps.presentation

import android.util.Log
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kz.cicada.berkut.feature.savedlocations.domain.repository.SavedLocationsRepository
import kz.cicada.berkut.feature.savedlocations.presentation.maps.SavedLocationsMapLauncher
import kz.cicada.berkut.feature.sos.presentation.navigation.HotlineNumberScreens
import kz.cicada.berkut.lib.core.data.local.UserPreferences
import kz.cicada.berkut.lib.core.data.network.UserType
import kz.cicada.berkut.lib.core.ui.base.BaseViewModel
import kz.cicada.berkut.lib.core.ui.extensions.tryToUpdate
import kz.cicada.berkut.lib.core.ui.navigation.cicerone.router.RouterFacade

class MapsViewModel(
    private val routerFacade: RouterFacade,
    private val preferences: UserPreferences,
    private val savedLocationsRepo: SavedLocationsRepository,
) : BaseViewModel() {
    val savedLocations = MutableStateFlow<List<SavedLocationsMapLauncher.SafeLocation>>(emptyList())

    val role = MutableStateFlow<UserType>(UserType.PARENT)

    init {
        viewModelScope.launch(Dispatchers.IO) {
            role.update {
                preferences.getType().map {
                    if (it == UserType.PARENT.name) {
                        UserType.PARENT
                    } else {
                        UserType.CHILD
                    }
                }.first()
            }

            when (preferences.getType().first()) {
                UserType.CHILD.name -> {
                    getSaveLocations(preferences.getId().first().toInt())
                }
            }
        }
    }

    private fun getSaveLocations(childId: Int) {
        networkRequest(
            request = {
                savedLocationsRepo.getSaveLocations(childId)
            },
            onSuccess = { response ->
                savedLocations.tryToUpdate {
                    response.map {
                        SavedLocationsMapLauncher.SafeLocation(
                            name = it.name,
                            radius = it.radius,
                            latitude = it.latitude,
                            longitude = it.longitude,
                        )
                    }
                }
            },
        )
    }

    fun onSOSClick() {
        routerFacade.navigateTo(
            HotlineNumberScreens.HoltineListOfNumbers()
        )
    }
}