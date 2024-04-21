package kz.cicada.berkut.feature.shareqr.presentation.share

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kz.cicada.berkut.feature.shareqr.presentation.share.socket.QRSocketModel
import kz.cicada.berkut.lib.core.data.local.UserPreferences
import kz.cicada.berkut.lib.core.data.network.UserType
import kz.cicada.berkut.lib.core.ui.base.BaseViewModel
import kz.cicada.berkut.lib.core.ui.extensions.tryToUpdate
import kz.cicada.berkut.lib.core.ui.navigation.cicerone.router.RouterFacade

class ShareQRViewModel(
    private val routerFacade: RouterFacade,
    private val userPreferences: UserPreferences,
): BaseViewModel(), ShareQRController {
    val uiState = MutableStateFlow<ShareQRUIState>(
        ShareQRUIState.Loading,
    )

    init {
        getQR()
    }

    private fun getQR() {
        viewModelScope.launch(Dispatchers.IO) {
            val id = userPreferences.getId().first()
            val url = "https://berkut.app/id=$id"
            uiState.tryToUpdate { ShareQRUIState.Data(url) }
        }
    }

    override fun saveDataAndExit(model: QRSocketModel) {
        viewModelScope.launch(Dispatchers.IO) {
            uiState.tryToUpdate { ShareQRUIState.Loading }
            model.parentInfo?.let {
                val link = it.links.firstOrNull()
                userPreferences.setSecondUserType(
                    id = it.id,
                    type = it.role ?: UserType.PARENT.name,
                    username = it.username.orEmpty(),
                    phoneNumber = it.phoneNumber,
                    rel = link?.rel.orEmpty(),
                    href = link?.href.orEmpty(),
                )
            }
            onNavigateBack()
        }
    }


    override fun onNavigateBack() {
        routerFacade.exit()
    }
}