package kz.cicada.berkut.feature.shareqr.presentation.scan

import kotlinx.coroutines.flow.first
import kz.cicada.berkut.feature.shareqr.data.repository.QRApi
import kz.cicada.berkut.lib.core.data.local.UserPreferences
import kz.cicada.berkut.lib.core.ui.base.BaseViewModel
import kz.cicada.berkut.lib.core.ui.event.ActionResultEvent

class ScanQRViewModel(
    private val api: QRApi,
    private val userPref: UserPreferences,
) : BaseViewModel(), ScanQRViewController {

    override fun detekt(url: String) {
        val childId = url.replace("https://berkut.app/id=", "").toIntOrNull() ?: -1
        if (url.contains("https://berkut.app/id=")) {
            networkRequest(
                request = {
                    val parentId = userPref.getId().first().toIntOrNull() ?: -1
                    api.addChild(
                        parentId,
                        childId,
                    )
                },
                onError = {
                    sendEvent(
                        ActionResultEvent(
                            ScanQREvent(
                                checked = false,
                                id = childId,
                            )
                        )
                    )
                },
                onSuccess = {
                    sendEvent(
                        ActionResultEvent(
                            ScanQREvent(
                                checked = true,
                                id = childId,
                            )
                        )
                    )
                },
            )
        }
    }
}