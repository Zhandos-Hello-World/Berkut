package kz.cicada.berkut.feature.profile.navigation

import kz.cicada.berkut.feature.profile.presentation.home.HomeFragment
import kz.cicada.berkut.feature.profile.presentation.hotline.AddHotlineNumbersFragment
import kz.cicada.berkut.feature.profile.presentation.hotline.listHotline.HotlineListFragment
import kz.cicada.berkut.feature.profile.presentation.logout.LogoutConfirmBottomSheetFragment
import kz.cicada.berkut.feature.profile.presentation.mission.OurMissionFragment
import kz.cicada.berkut.feature.profile.presentation.privacy.PrivacyFragment
import kz.cicada.berkut.feature.profile.presentation.support.SupportFragment
import kz.cicada.berkut.lib.core.ui.navigation.cicerone.screen.BerkutDialogFragmentScreen
import kz.cicada.berkut.lib.core.ui.navigation.cicerone.screen.BerkutFragmentScreen

object ProfileScreens {

    fun AddHotlineNumbers(): BerkutFragmentScreen = BerkutFragmentScreen {
        AddHotlineNumbersFragment()
    }

    fun HoltineListOfNumbers(): BerkutFragmentScreen = BerkutFragmentScreen {
        HotlineListFragment()
    }

    fun Privacy(): BerkutFragmentScreen = BerkutFragmentScreen {
        PrivacyFragment()
    }

    fun Mission(): BerkutFragmentScreen = BerkutFragmentScreen {
        OurMissionFragment()
    }

    fun Home(): BerkutFragmentScreen = BerkutFragmentScreen {
        HomeFragment()
    }

    fun Support(): BerkutFragmentScreen = BerkutFragmentScreen {
        SupportFragment()
    }

    fun LogoutConfirm(): BerkutDialogFragmentScreen = BerkutDialogFragmentScreen {
        LogoutConfirmBottomSheetFragment()
    }
}