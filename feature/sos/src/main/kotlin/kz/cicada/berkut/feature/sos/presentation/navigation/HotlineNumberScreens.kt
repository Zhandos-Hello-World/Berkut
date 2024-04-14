package kz.cicada.berkut.feature.sos.presentation.navigation

import kz.cicada.berkut.feature.sos.presentation.hotline.AddHotlineNumbersFragment
import kz.cicada.berkut.feature.sos.presentation.hotline.listHotline.HotlineListFragment
import kz.cicada.berkut.lib.core.ui.navigation.cicerone.screen.BerkutFragmentScreen

object HotlineNumberScreens {

    fun AddHotlineNumbers(): BerkutFragmentScreen = BerkutFragmentScreen {
        AddHotlineNumbersFragment()
    }

    fun HoltineListOfNumbers(): BerkutFragmentScreen = BerkutFragmentScreen {
        HotlineListFragment()
    }

}