package kz.cicada.berkut.feature.shareqr.presentation

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kz.cicada.berkut.lib.core.data.local.UserPreferences
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
            val id = userPreferences.getId()
            val url = "https://berkut.app/id=$id"
            uiState.tryToUpdate { ShareQRUIState.Data(url) }
        }
    }

    override fun onNavigateBack() {
        routerFacade.exit()
    }
}