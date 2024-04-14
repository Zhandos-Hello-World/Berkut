package kz.cicada.berkut.feature.savedlocations.presentation.maps

import kz.cicada.berkut.feature.savedlocations.navigation.SavedLocationsScreens
import kz.cicada.berkut.feature.savedlocations.presentation.confirm.SavedLocationsConfirmLauncher
import kz.cicada.berkut.feature.savedlocations.presentation.confirm.SavedLocationsConfirmResultEvent
import kz.cicada.berkut.lib.core.ui.base.BaseViewModel
import kz.cicada.berkut.lib.core.ui.event.CloseScreenEvent
import kz.cicada.berkut.lib.core.ui.event.OpenScreenEvent

class SavedLocationsMapViewModel : BaseViewModel() {

    fun navigateToConfirm(
        latitude: Double,
        longitude: Double,
        radius: Double,
    ) {
        sendEvent(
            OpenScreenEvent(
                screen = SavedLocationsScreens.SavedLocationConfirm(
                    launcher = SavedLocationsConfirmLauncher(
                        latitude = latitude,
                        longitude = longitude,
                        radius = radius,
                    ),
                ),
            )
        )
    }

    override fun onNavigationResult(result: Any) {
        super.onNavigationResult(result)
        if (result == SavedLocationsConfirmResultEvent) {
            navigateUp()
        }
    }

    fun navigateUp() {
        sendEvent(CloseScreenEvent)
    }
}