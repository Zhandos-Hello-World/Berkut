package kz.cicada.berkut.lib.core.ui.compose.external.app.service

import kotlinx.coroutines.flow.Flow

interface ExternalAppService {

    val galleryEvents: Flow<PhotoPickerResultEvent>
    val cameraEvents: Flow<CameraResultEvent>

    fun openGallery()
    fun checkPermissionAndOpenCamera()
}
