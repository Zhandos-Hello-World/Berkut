package kz.cicada.berkut.feature.profile.presentation.home

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kz.cicada.berkut.feature.profile.navigation.ProfileScreens
import kz.cicada.berkut.feature.profile.presentation.logout.LogoutConfirmEvent
import kz.cicada.berkut.feature.shareqr.navigation.QRScreens
import kz.cicada.berkut.feature.sos.presentation.navigation.HotlineNumberScreens
import kz.cicada.berkut.lib.core.data.local.UserPreferences
import kz.cicada.berkut.lib.core.data.network.UserType
import kz.cicada.berkut.lib.core.ui.base.BaseViewModel
import kz.cicada.berkut.lib.core.ui.event.CloseScreenEvent
import kz.cicada.berkut.lib.core.ui.event.OpenAuthFlowEvent
import kz.cicada.berkut.lib.core.ui.event.OpenScreenEvent
import kz.cicada.berkut.lib.core.ui.extensions.tryToUpdate

class HomeViewModel(
    private val userPreferences: UserPreferences,

) : BaseViewModel(), HomeController {
    val state = MutableStateFlow<HomeUIState>(HomeUIState.NotInit)

    init {
        getUserType()
    }

    private fun getUserType() {
        viewModelScope.launch {
            val isParent = userPreferences.getType().first() == UserType.PARENT.name
            if (isParent) {
                state.tryToUpdate { HomeUIState.ParentData }
            } else {
                state.tryToUpdate { HomeUIState.ChildData }
            }
        }
    }

    override fun navigateToHotlineNumbers() = sendEvent(OpenScreenEvent(HotlineNumberScreens.AddHotlineNumbers()))
    override fun navigateToScanQr() = sendEvent(OpenScreenEvent(QRScreens.scanQRScreen()))
    override fun navigateToShareQR() = sendEvent(OpenScreenEvent(QRScreens.qRScreen()))
    override fun navigateUp() = sendEvent(CloseScreenEvent)
    override fun navigateToPrivacy() = sendEvent(OpenScreenEvent(ProfileScreens.Privacy()))
    override fun navigateToSupport() = sendEvent(OpenScreenEvent(ProfileScreens.Support()))
    override fun navigateToFAQ() = sendEvent(OpenScreenEvent(ProfileScreens.FAQ()))
    override fun onLogoutClick() = sendEvent(OpenScreenEvent(ProfileScreens.LogoutConfirm()))
    override fun navigateToAddTask() {
        //TODO
    }
    override fun navigateToAddSaveLocations() {
        //TODO
    }

    override fun onNavigationResult(result: Any) {
        super.onNavigationResult(result)
        when (result) {
            is LogoutConfirmEvent -> {
                if (result.logout) {
                    logoutAndNavigateToOnboarding()
                }
            }
        }
    }

    private fun logoutAndNavigateToOnboarding() {
        viewModelScope.launch(Dispatchers.IO) {
            userPreferences.clearAllData()
            withContext(Dispatchers.Main) {
                sendEvent(OpenAuthFlowEvent)
            }
        }
    }
}