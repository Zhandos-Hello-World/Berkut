package kz.cicada.berkut.lib.core.ui.compose.external.app.service

import android.graphics.Bitmap
import android.net.Uri

sealed interface CameraResultEvent {
    class SuccessCameraResult(
        val bitmap: Bitmap,
        val uri: Uri,
    ) : CameraResultEvent
    object PermissionDeniedResult : CameraResultEvent
}