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
import kz.cicada.berkut.feature.uploadPhoto.BuildConfig
import kz.cicada.berkut.feature.uploadphoto.presentation.settings.AvatarSettings
import kz.cicada.berkut.feature.uploadphoto.presentation.settings.AvatarSettingsChooserBehavior
import kz.cicada.berkut.feature.uploadphoto.presentation.settings.AvatarSettingsResultEvent
import kz.cicada.berkut.feature.uploadphoto.utils.ExternalRemoteImageStreamService
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
    private val externalRemoteImage: ExternalRemoteImageStreamService,
    private val userPreferences: UserPreferences,
    private val repository: ProfileRepository,
) : BaseViewModel(), ProfileController {
    val uiState = MutableStateFlow<ProfIleUIState>(ProfIleUIState.Data())
    private var cachedUsername: String = ""
    private var imageChanged: Boolean = false

    init {
        handleCameraEvents()
        handleGalleryEvents()
        getData()
    }

    override fun onNavigateBack() {
        sendEvent(CloseScreenEvent)
    }

    override fun changeUsername(username: String) {
        if (getDataState()?.loadingContinueButton != true) {
            val data: ProfIleUIState = getDataState()?.copy(
                username = username,
                enabled = username.isNotEmpty(),
            ) ?: ProfIleUIState.Loading
            uiState.tryToUpdate { data }
        }
    }

    override fun changeProfile() {
        val username = getDataState()?.username ?: return
        networkRequest(
            request = {
                uiState.tryToUpdate {
                    getDataState()?.copy(
                        loadingContinueButton = true,
                        enabled = true,
                    ) ?: ProfIleUIState.Loading
                }
                repository.updateProfile(
                    username = username,
                    avatarUri = if (imageChanged) getDataState()?.avatarUri else null,
                )
            },
            onSuccess = {
                userPreferences.setUsername(username = username)
            },
            onError = {
                      it.printStackTrace()
            },
            finally = {
                uiState.tryToUpdate {
                    getDataState()?.copy(
                        loadingContinueButton = false,
                        enabled = false,
                    ) ?: ProfIleUIState.Loading
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

            externalRemoteImage.getImage(
                url = BuildConfig.BERKUT_BASE_URL + "users/${id}/profile-photo",
                onResponse = { response ->
                    uiState.tryToUpdate {
                        ProfIleUIState.Data(
                            userId = id,
                            avatar = response,
                            username = cachedUsername,
                            phoneNumber = phoneNumber,
                            loadingContinueButton = false,
                            enabled = false,
                        )
                    }
                },
            )
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
        imageChanged = true
        uiState.tryToUpdate {
            getDataState()?.copy(
                avatar = bitmap,
                avatarUri = uri,
                enabled = true,
            ) ?: ProfIleUIState.Loading
        }
    }


    private fun getDataState(): ProfIleUIState.Data? {
        return uiState.value as? ProfIleUIState.Data
    }
}