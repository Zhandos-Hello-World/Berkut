package kz.cicada.berkut.feature.maps.presentation

import kz.cicada.berkut.feature.shareqr.navigation.QRScreens
import kz.cicada.berkut.feature.uploadphoto.navigation.AddAvatarScreen
import kz.cicada.berkut.lib.core.ui.base.BaseViewModel
import kz.cicada.berkut.lib.core.ui.navigation.cicerone.router.RouterFacade

class MapsViewModel(
    private val routerFacade: RouterFacade,
): BaseViewModel() {

    init {
//        routerFacade.navigateTo(
//            QRScreens.qRScreen(),
//        )
    }
}