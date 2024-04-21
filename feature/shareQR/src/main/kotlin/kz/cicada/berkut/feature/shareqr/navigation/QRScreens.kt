package kz.cicada.berkut.feature.shareqr.navigation

import kz.cicada.berkut.feature.shareqr.presentation.scan.ScanQRFragment
import kz.cicada.berkut.feature.shareqr.presentation.share.ShareQRFragment
import kz.cicada.berkut.lib.core.ui.navigation.cicerone.screen.BerkutDialogFragmentScreen

object QRScreens {

    fun qRScreen(): BerkutDialogFragmentScreen = BerkutDialogFragmentScreen {
        ShareQRFragment()
    }

    fun scanQRScreen(): BerkutDialogFragmentScreen = BerkutDialogFragmentScreen {
        ScanQRFragment()
    }
}