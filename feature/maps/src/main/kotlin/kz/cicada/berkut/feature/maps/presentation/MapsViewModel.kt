package kz.cicada.berkut.feature.maps.presentation

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kz.cicada.berkut.lib.core.data.network.UserType
import kz.cicada.berkut.feature.shareqr.navigation.QRScreens
import kz.cicada.berkut.lib.core.data.local.UserPreferences
import kz.cicada.berkut.lib.core.ui.base.BaseViewModel
import kz.cicada.berkut.lib.core.ui.navigation.cicerone.router.RouterFacade

class MapsViewModel(
    private val routerFacade: RouterFacade,
    private val preferences: UserPreferences,
) : BaseViewModel() {

    init {
        viewModelScope.launch(Dispatchers.IO) {
            when (preferences.getType().first()) {
                UserType.CHILD.name -> {
                    routerFacade.navigateTo(
                        QRScreens.qRScreen(),
                    )
                }

                UserType.PARENT.name -> {
                    routerFacade.navigateTo(
                        QRScreens.scanQRScreen(),
                    )
                }
            }
        }
    }
}