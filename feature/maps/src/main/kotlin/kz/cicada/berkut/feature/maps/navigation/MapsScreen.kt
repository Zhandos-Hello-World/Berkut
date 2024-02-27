package kz.cicada.berkut.feature.maps.navigation

import kz.cicada.berkut.feature.maps.presentation.MapsFragment
import kz.cicada.berkut.lib.core.ui.navigation.cicerone.screen.BerkutFragmentScreen

object MapsScreen  {

    fun Main(): BerkutFragmentScreen = BerkutFragmentScreen {
        MapsFragment()
    }
}