package kz.cicada.berkut.feature.savedlocations.navigation

import kz.cicada.berkut.feature.savedlocations.presentation.confirm.SavedLocationsConfirmBottomSheet
import kz.cicada.berkut.feature.savedlocations.presentation.confirm.SavedLocationsConfirmLauncher
import kz.cicada.berkut.feature.savedlocations.presentation.list.SaveLocationListFragment
import kz.cicada.berkut.feature.savedlocations.presentation.list.SaveLocationListLauncher
import kz.cicada.berkut.feature.savedlocations.presentation.maps.SavedLocationsMapFragment
import kz.cicada.berkut.feature.savedlocations.presentation.maps.SavedLocationsMapLauncher
import kz.cicada.berkut.lib.core.ui.navigation.cicerone.screen.BerkutDialogFragmentScreen
import kz.cicada.berkut.lib.core.ui.navigation.cicerone.screen.BerkutFragmentScreen

object SavedLocationsScreens {

    fun AllSaveLocations(launcher: SaveLocationListLauncher): BerkutFragmentScreen = BerkutFragmentScreen {
        SaveLocationListFragment(
            launcher,
        )
    }

    fun SavedLocationsMap(launcher: SavedLocationsMapLauncher): BerkutFragmentScreen = BerkutFragmentScreen {
        SavedLocationsMapFragment(launcher)
    }

    fun SavedLocationConfirm(launcher: SavedLocationsConfirmLauncher): BerkutDialogFragmentScreen =
        BerkutDialogFragmentScreen {
            SavedLocationsConfirmBottomSheet(launcher)
        }
}