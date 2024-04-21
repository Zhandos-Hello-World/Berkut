package kz.cicada.berkut.feature.uploadphoto.presentation.navigation

import kz.cicada.berkut.feature.uploadphoto.presentation.add.AddAvatarFragment
import kz.cicada.berkut.lib.core.ui.navigation.cicerone.screen.BerkutFragmentScreen

object AddAvatarScreen {

    fun AddAvatar(): BerkutFragmentScreen = BerkutFragmentScreen {
        AddAvatarFragment()
    }
}