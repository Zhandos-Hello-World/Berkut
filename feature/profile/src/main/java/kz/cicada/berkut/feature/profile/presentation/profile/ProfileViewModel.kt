package kz.cicada.berkut.feature.profile.presentation.profile

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kz.cicada.berkut.feature.profile.domain.repository.ProfileRepository
import kz.cicada.berkut.lib.core.data.local.UserPreferences
import kz.cicada.berkut.lib.core.ui.base.BaseViewModel
import kz.cicada.berkut.lib.core.ui.event.CloseScreenEvent
import kz.cicada.berkut.lib.core.ui.extensions.tryToUpdate

class ProfileViewModel(
    private val userPreferences: UserPreferences,
    private val repository: ProfileRepository,
) : BaseViewModel(), ProfileController {
    val uiState = MutableStateFlow<ProfIleUIState.Data>(
        ProfIleUIState.Data(
            userId = 0,
            username = "",
            phoneNumber = "",
            loading = false,
            enabled = false,
        ),
    )
    private var cachedUsername: String = ""

    init {
        getData()
    }

    override fun onNavigateBack() {
        sendEvent(CloseScreenEvent)
    }

    override fun changeUsername(username: String) {
        if (!uiState.value.loading) {
            uiState.tryToUpdate {
                uiState.value.copy(
                    username = username,
                    enabled = username != cachedUsername && username.isNotEmpty(),
                )
            }
        }
    }

    override fun changeProfile() {
        val username = uiState.value.username
        networkRequest(
            request = {
                uiState.tryToUpdate {
                    uiState.value.copy(
                        loading = true,
                        enabled = true,
                    )
                }
                repository.updateProfile(username = username)
            },
            onSuccess = {
                userPreferences.setUsername(username = username)
            },
            finally = {
                uiState.tryToUpdate {
                    uiState.value.copy(
                        loading = false,
                        enabled = false,
                    )
                }
            },
        )
    }

    private fun getData() {
        viewModelScope.launch {
            cachedUsername = userPreferences.getUserName().first()
            val id = userPreferences.getId().first().toInt()
            val phoneNumber = userPreferences.getUserPhoneNumber().first()

            uiState.tryToUpdate {
                ProfIleUIState.Data(
                    userId = id,
                    username = cachedUsername,
                    phoneNumber = phoneNumber,
                    loading = false,
                    enabled = false,
                )
            }
        }
    }
}