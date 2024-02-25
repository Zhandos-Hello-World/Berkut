package kz.cicada.berkut.feature.result.presentation.navigation

import kz.cicada.berkut.feature.result.presentation.feature.ResultFragment
import kz.cicada.berkut.feature.result.presentation.feature.ResultLauncher
import kz.cicada.berkut.lib.core.ui.navigation.cicerone.screen.BerkutFragmentScreen

object ResultScreens {

    fun Result(launcher: ResultLauncher): BerkutFragmentScreen = BerkutFragmentScreen {
        ResultFragment(launcher)
    }
}