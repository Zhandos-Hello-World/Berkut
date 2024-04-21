package kz.cicada.berkut.feature.profile.navigation

import kz.cicada.berkut.feature.profile.presentation.faq.FAQFragment
import kz.cicada.berkut.feature.profile.presentation.home.HomeFragment
import kz.cicada.berkut.feature.profile.presentation.logout.LogoutConfirmBottomSheetFragment
import kz.cicada.berkut.feature.profile.presentation.mission.OurMissionFragment
import kz.cicada.berkut.feature.profile.presentation.privacy.PrivacyFragment
import kz.cicada.berkut.feature.profile.presentation.profile.ProfileFragment
import kz.cicada.berkut.feature.profile.presentation.support.SupportFragment
import kz.cicada.berkut.lib.core.ui.navigation.cicerone.screen.BerkutDialogFragmentScreen
import kz.cicada.berkut.lib.core.ui.navigation.cicerone.screen.BerkutFragmentScreen

object ProfileScreens {
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

    fun Profile() : BerkutFragmentScreen = BerkutFragmentScreen {
        ProfileFragment()
    }

    fun FAQ() : BerkutFragmentScreen = BerkutFragmentScreen {
        FAQFragment()
    }
}