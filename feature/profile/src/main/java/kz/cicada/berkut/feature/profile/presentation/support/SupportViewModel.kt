package kz.cicada.berkut.feature.profile.presentation.support

import android.util.Log
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kz.cicada.berkut.feature.profile.navigation.ProfileScreens
import kz.cicada.berkut.lib.core.data.local.UserPreferences
import kz.cicada.berkut.lib.core.ui.base.BaseViewModel
import kz.cicada.berkut.lib.core.ui.compose.extension.tryToUpdate
import kz.cicada.berkut.lib.core.ui.event.CloseScreenEvent
import kz.cicada.berkut.lib.core.ui.event.OpenScreenEvent

class SupportViewModel(
    private val userPreferences: UserPreferences,
) : BaseViewModel(), SupportController {
    val uiState = MutableStateFlow<SupportUIState>(SupportUIState.NotInit)

    init {
        fetchUserId()
    }

    override fun navigateUp() = sendEvent(CloseScreenEvent)
    override fun navigateToOurMission() = sendEvent(OpenScreenEvent(ProfileScreens.Mission()))
    override fun navigateToPrivacyPolicy() = sendEvent(OpenScreenEvent(ProfileScreens.Privacy()))
    override fun navigateToYourId() = sendEvent(OpenScreenEvent(ProfileScreens.Profile()))

    private fun fetchUserId() {
        viewModelScope.launch {
            val id = userPreferences.getId().first()
            Log.d("YOURID", id.toString())
            uiState.tryToUpdate {
                SupportUIState.Data(
                    id = id,
                )
            }
        }
    }
}