package kz.cicada.berkut.feature.children.navigation

import kz.cicada.berkut.feature.children.presentation.childs.ChildrenFragment
import kz.cicada.berkut.feature.children.presentation.childs.ChildrenLauncher
import kz.cicada.berkut.feature.children.presentation.details.ChildDetailsBottomSheetFragment
import kz.cicada.berkut.feature.children.presentation.details.ChildDetailsLauncher
import kz.cicada.berkut.lib.core.ui.navigation.cicerone.screen.BerkutDialogFragmentScreen
import kz.cicada.berkut.lib.core.ui.navigation.cicerone.screen.BerkutFragmentScreen

object ChildrenScreens {

    fun ChildrenScreen(launcher: ChildrenLauncher): BerkutFragmentScreen = BerkutFragmentScreen {
        ChildrenFragment(
            launcher
        )
    }

    fun ChildDetailScreen(launcher: ChildDetailsLauncher): BerkutDialogFragmentScreen =
        BerkutDialogFragmentScreen {
            ChildDetailsBottomSheetFragment(launcher)
        }
}