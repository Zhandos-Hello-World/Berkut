package kz.cicada.berkut.feature.maps.presentation

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kz.cicada.berkut.feature.shareqr.presentation.scan.ScanQREvent
import kz.cicada.berkut.feature.sos.presentation.navigation.HotlineNumberScreens
import kz.cicada.berkut.lib.core.data.local.UserPreferences
import kz.cicada.berkut.lib.core.data.network.UserType
import kz.cicada.berkut.lib.core.ui.base.BaseViewModel
import kz.cicada.berkut.lib.core.ui.navigation.cicerone.router.RouterFacade

class MapsViewModel(
    private val routerFacade: RouterFacade,
    private val preferences: UserPreferences,
) : BaseViewModel() {

    val role = MutableStateFlow<UserType>(UserType.PARENT)

    init {
        viewModelScope.launch(Dispatchers.IO) {
            role.update {
                preferences.getType().map {
                    if (it == UserType.PARENT.name) {
                        UserType.PARENT
                    } else {
                        UserType.CHILD
                    }
                }.first()
            }

//            when (preferences.getType().first()) {
//                UserType.CHILD.name -> {
//                    routerFacade.navigateTo(
//                        QRScreens.qRScreen(),
//                    )
////                    routerFacade.navigateTo(AddAvatar())
//                }
//
//                UserType.PARENT.name -> {
//                    routerFacade.navigateTo(
//                        QRScreens.scanQRScreen(),
//                    )
//                }
//            }
        }
    }



    fun onSOSClick() {
        routerFacade.navigateTo(
            HotlineNumberScreens.HoltineListOfNumbers()
        )
    }
}