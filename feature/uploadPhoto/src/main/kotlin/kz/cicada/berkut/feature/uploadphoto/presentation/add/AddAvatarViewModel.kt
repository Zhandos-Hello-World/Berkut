package kz.cicada.berkut.feature.uploadphoto.presentation.add

import kz.cicada.berkut.feature.uploadphoto.presentation.settings.AvatarSettings
import kz.cicada.berkut.feature.uploadphoto.presentation.settings.AvatarSettingsChooserBehavior
import kz.cicada.berkut.feature.uploadphoto.presentation.settings.AvatarSettingsResultEvent
import android.graphics.Bitmap
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kz.cicada.berkut.lib.core.ui.base.BaseViewModel
import kz.cicada.berkut.lib.core.ui.compose.extension.tryToUpdate
import kz.cicada.berkut.lib.core.ui.compose.external.app.service.CameraResultEvent
import kz.cicada.berkut.lib.core.ui.compose.external.app.service.ExternalAppService
import kz.cicada.berkut.lib.core.ui.event.SystemEvent

class AddAvatarViewModel(
    private val externalAppService: ExternalAppService,
) : BaseViewModel(), AddAvatarController {

    private val _uiState = MutableStateFlow(AddAvatarUiState())
    internal val uiState: StateFlow<AddAvatarUiState> = _uiState.asStateFlow()

    init {
        handleSystemEvents()
        handleCameraEvents()
        handleGalleryEvents()
    }

    override fun onAddAvatarButtonIconClick() {
        sendEvent(
//            OpenBottomSheetEvent(
//                SimpleChooserScreen(
//                    SimpleChooserLauncher(
//                        AvatarSettingsChooserBehavior(),
//                    ),
//                ),
//            ),
        )
    }

    override fun onContinueButtonClick() {
        // TODO: Отправить на backend полученный Bitmap
        // TODO: Navigate to home screen
    }

    override fun onSkipButtonClick() {
        // TODO: Navigate to home screen
    }

    override fun onNavigateBack() {
//        sendEvent(NavigateBackEvent)
    }

    private fun handleSystemEvents() {
        viewModelScope.launch {
//            eventBus.systemEvents.collect { event ->
//                collectSystemEvents(event)
//            }
        }
    }

    private fun handleCameraEvents() {
        viewModelScope.launch(Dispatchers.IO) {
            externalAppService.cameraEvents.collect {
                if (it is CameraResultEvent.SuccessCameraResult) {
                    handleAvatar(it.bitmap)
                } else {
                    externalAppService.checkPermissionAndOpenCamera()
                }
            }
        }
    }

    private fun handleGalleryEvents() {
        viewModelScope.launch(Dispatchers.IO) {
            externalAppService.galleryEvents.collect {
                handleAvatar(it)
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

    private fun handleAvatar(bitmap: Bitmap?) {
        _uiState.tryToUpdate {
            uiState.value.copy(avatar = bitmap)
        }
    }
}