package kz.cicada.berkut.feature.profile.presentation.profile

import android.graphics.Bitmap
import android.net.Uri
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kz.cicada.berkut.feature.chooser.presentation.feature.navigation.SimpleChooseScreens
import kz.cicada.berkut.feature.chooser.presentation.feature.simple.SimpleChooserLauncher
import kz.cicada.berkut.feature.profile.domain.repository.ProfileRepository
import kz.cicada.berkut.feature.uploadphoto.presentation.settings.AvatarSettings
import kz.cicada.berkut.feature.uploadphoto.presentation.settings.AvatarSettingsChooserBehavior
import kz.cicada.berkut.feature.uploadphoto.presentation.settings.AvatarSettingsResultEvent
import kz.cicada.berkut.lib.core.data.local.UserPreferences
import kz.cicada.berkut.lib.core.ui.base.BaseViewModel
import kz.cicada.berkut.lib.core.ui.compose.extension.tryToUpdate
import kz.cicada.berkut.lib.core.ui.compose.external.app.service.CameraResultEvent
import kz.cicada.berkut.lib.core.ui.compose.external.app.service.ExternalAppService
import kz.cicada.berkut.lib.core.ui.compose.external.app.service.PhotoPickerResultEvent
import kz.cicada.berkut.lib.core.ui.event.CloseScreenEvent
import kz.cicada.berkut.lib.core.ui.event.OpenScreenEvent
import kz.cicada.berkut.lib.core.ui.event.SystemEvent

class ProfileViewModel(
    private val externalAppService: ExternalAppService,
    private val userPreferences: UserPreferences,
    private val repository: ProfileRepository,
) : BaseViewModel(), ProfileController {
    val uiState = MutableStateFlow<ProfIleUIState.Data>(ProfIleUIState.Data())
    private var cachedUsername: String = ""

    init {
        handleCameraEvents()
        handleGalleryEvents()
        getData()
    }

    override fun onNavigateBack() {
        sendEvent(CloseScreenEvent)
    }

    override fun changeUsername(username: String) {
        if (!uiState.value.loadingContinueButton) {
            val data: ProfIleUIState.Data = uiState.value.copy(
                username = username,
                enabled = username != cachedUsername && username.isNotEmpty(),
            )
            uiState.tryToUpdate { data }
        }
    }

    override fun changeProfile() {
        val username = uiState.value.username
        networkRequest(
            request = {
                uiState.tryToUpdate {
                    uiState.value.copy(
                        loadingContinueButton = true,
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
                        loadingContinueButton = false,
                        enabled = false,
                    )
                }
            },
        )
    }

    override fun onAddAvatarButtonIconClick() {
        sendEvent(
            OpenScreenEvent(
                SimpleChooseScreens.SimpleChoose(
                    SimpleChooserLauncher(
                        behavior = AvatarSettingsChooserBehavior(),
                    )
                )
            )
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
                    loadingContinueButton = false,
                    enabled = false,
                )
            }
        }
    }

    override fun onNavigationResult(result: Any) {
        when (result) {
            is SystemEvent -> collectSystemEvents(result)
        }
    }


    private fun handleCameraEvents() {
        viewModelScope.launch(Dispatchers.IO) {
            externalAppService.cameraEvents.collect {
                if (it is CameraResultEvent.SuccessCameraResult) {
                    handleAvatar(
                        bitmap = it.bitmap,
                        uri = it.uri,
                    )
                } else {
                    externalAppService.checkPermissionAndOpenCamera()
                }
            }
        }
    }

    private fun handleGalleryEvents() {
        viewModelScope.launch(Dispatchers.IO) {
            externalAppService.galleryEvents.collect {
                if (it is PhotoPickerResultEvent.SuccessPhotoResult) {
                    handleAvatar(
                        bitmap = it.bitmap,
                        uri = it.uri,
                    )
                } else {
                    externalAppService.checkPermissionAndOpenCamera()
                }
            }
        }
    }

    private fun collectSystemEvents(event: SystemEvent) {
        when (event) {
            is AvatarSettingsResultEvent -> onChoosingAvatarSettings(event.avatarSettings)
            else -> Unit
        }
    }

    private fun onChoosingAvatarSettings(avatarSettings: AvatarSettings) {
        when (avatarSettings) {
            AvatarSettings.OPEN_CAMERA -> externalAppService.checkPermissionAndOpenCamera()
            AvatarSettings.OPEN_GALLERY -> externalAppService.openGallery()
        }
    }

    private fun handleAvatar(
        bitmap: Bitmap?,
        uri: Uri,
    ) {
        uiState.tryToUpdate {
            uiState.value.copy(
                avatar = bitmap,
                avatarUri = uri,
            )
        }
    }
}