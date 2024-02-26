package kz.cicada.berkut.feature.language.navigation

import kz.cicada.berkut.lib.core.ui.navigation.cicerone.screen.BerkutFragmentScreen
import kz.cicada.berkut.feature.language.presentation.onboarding.OnboardingLanguageFragment
import kz.cicada.berkut.feature.language.presentation.role.ChooseRoleFragment

object LanguageScreens {

    fun Onboarding(): BerkutFragmentScreen = BerkutFragmentScreen {
        OnboardingLanguageFragment()
    }

    fun ChooseRole(): BerkutFragmentScreen = BerkutFragmentScreen {
        ChooseRoleFragment()
    }
}