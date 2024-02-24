package kz.cicada.berkut.lib.core.ui.compose.external.app.service

import android.graphics.Bitmap

sealed interface CameraResultEvent {
    class SuccessCameraResult(val bitmap: Bitmap) : CameraResultEvent
    object PermissionDeniedResult : CameraResultEvent
}