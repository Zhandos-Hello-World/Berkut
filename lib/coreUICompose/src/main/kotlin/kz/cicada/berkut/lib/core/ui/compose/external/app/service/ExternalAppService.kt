package kz.cicada.berkut.lib.core.ui.compose.external.app.service

import android.graphics.Bitmap
import kotlinx.coroutines.flow.Flow

interface ExternalAppService {

    val galleryEvents: Flow<Bitmap>
    val cameraEvents: Flow<CameraResultEvent>

    fun openGallery()
    fun checkPermissionAndOpenCamera()
}