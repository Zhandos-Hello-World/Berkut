package kz.cicada.berkut.lib.core.ui.compose.external.app.service

import android.graphics.Bitmap
import android.net.Uri

sealed interface PhotoPickerResultEvent {

    class SuccessPhotoResult(
        val bitmap: Bitmap,
        val uri: Uri,
    ) : PhotoPickerResultEvent
    object PermissionDeniedResult : PhotoPickerResultEvent
}