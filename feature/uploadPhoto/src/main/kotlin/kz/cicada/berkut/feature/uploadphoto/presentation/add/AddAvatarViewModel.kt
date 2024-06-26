package kz.cicada.berkut.feature.uploadphoto.presentation.add

import android.graphics.Bitmap
import android.net.Uri
import android.util.Log
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kz.cicada.berkut.feature.chooser.presentation.feature.navigation.SimpleChooseScreens
import kz.cicada.berkut.feature.chooser.presentation.feature.simple.SimpleChooserLauncher
import kz.cicada.berkut.feature.uploadphoto.domain.PhotoRepository
import kz.cicada.berkut.feature.uploadphoto.presentation.settings.AvatarSettings
import kz.cicada.berkut.feature.uploadphoto.presentation.settings.AvatarSettingsChooserBehavior
import kz.cicada.berkut.feature.uploadphoto.presentation.settings.AvatarSettingsResultEvent
import kz.cicada.berkut.lib.core.ui.base.BaseViewModel
import kz.cicada.berkut.lib.core.ui.compose.extension.tryToUpdate
import kz.cicada.berkut.lib.core.ui.compose.external.app.service.CameraResultEvent
import kz.cicada.berkut.lib.core.ui.compose.external.app.service.ExternalAppService
import kz.cicada.berkut.lib.core.ui.compose.external.app.service.PhotoPickerResultEvent
import kz.cicada.berkut.lib.core.ui.event.OpenScreenEvent
import kz.cicada.berkut.lib.core.ui.event.SystemEvent
import kz.cicada.berkut.lib.core.ui.navigation.cicerone.router.RouterFacade

internal class AddAvatarViewModel(
    private val externalAppService: ExternalAppService,
    private val photoRepo: PhotoRepository,
    private val routerFacade: RouterFacade,
) : BaseViewModel(), AddAvatarController {

    private val _uiState = MutableStateFlow(AddAvatarUiState())
    internal val uiState: StateFlow<AddAvatarUiState> = _uiState.asStateFlow()

    init {
        handleCameraEvents()
        handleGalleryEvents()
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

    override fun onContinueButtonClick() {
        networkRequest(
            request = {
                setLoadingState(true)
                val uri = _uiState.value.avatarUri
                photoRepo.uploadPhoto(
                    userAvatarUri = uri,
                )
            },
            onError = {
                it.printStackTrace()
            },
            finally = {
                setLoadingState(false)
                routerFacade.exit()
            },
        )
    }

    override fun onSkipButtonClick() = routerFacade.exit()
    override fun onNavigateBack() = routerFacade.exit()

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
        _uiState.tryToUpdate {
            uiState.value.copy(
                avatar = bitmap,
                avatarUri = uri,
            )
        }
    }

    private fun setLoadingState(value: Boolean) {
        _uiState.tryToUpdate {
            uiState.value.copy(loadingContinueButton = value)
        }
    }
}