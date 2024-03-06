package kz.cicada.berkut.feature.shareqr.presentation.scan

import kotlinx.coroutines.flow.first
import kz.cicada.berkut.feature.shareqr.data.repository.QRApi
import kz.cicada.berkut.lib.core.data.local.UserPreferences
import kz.cicada.berkut.lib.core.ui.base.BaseViewModel
import kz.cicada.berkut.lib.core.ui.navigation.cicerone.router.RouterFacade

class ScanQRViewModel(
    private val api: QRApi,
    private val userPref: UserPreferences,
    private val routerFacade: RouterFacade,
) : BaseViewModel(), ScanQRViewController {

    override fun detekt(url: String) {
        if (url.contains("https://berkut.app/id=")) {
            networkRequest(
                request = {
                    val parentId = userPref.getId().first().toIntOrNull() ?: -1
                    val childId = url.replace("https://berkut.app/id=", "").toIntOrNull() ?: -1
                    api.addChild(
                        parentId,
                        childId,
                    )
                },
                onError = {
                    routerFacade.exit()
                },
                onSuccess = {
                    routerFacade.exit()
                }
            )
        }
    }
}