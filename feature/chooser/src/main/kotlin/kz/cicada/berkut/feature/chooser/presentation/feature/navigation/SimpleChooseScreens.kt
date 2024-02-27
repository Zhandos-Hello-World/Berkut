package kz.cicada.berkut.feature.chooser.presentation.feature.navigation

import kz.cicada.berkut.feature.chooser.presentation.feature.simple.SimpleChooserLauncher
import kz.cicada.berkut.feature.chooser.presentation.feature.simple.ui.SimpleChooserFragment
import kz.cicada.berkut.lib.core.ui.navigation.cicerone.screen.BerkutDialogFragmentScreen

object SimpleChooseScreens {

    fun SimpleChoose(launcher: SimpleChooserLauncher): BerkutDialogFragmentScreen =
        BerkutDialogFragmentScreen {
            SimpleChooserFragment(launcher)
        }

}