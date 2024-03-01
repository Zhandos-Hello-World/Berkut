package kz.cicada.berkut.feature.shareqr.navigation

import kz.cicada.berkut.feature.shareqr.presentation.ShareQRFragment
import kz.cicada.berkut.lib.core.ui.navigation.cicerone.screen.BerkutDialogFragmentScreen

object QRScreens {

    fun qRScreen(): BerkutDialogFragmentScreen = BerkutDialogFragmentScreen {
        ShareQRFragment()
    }
}