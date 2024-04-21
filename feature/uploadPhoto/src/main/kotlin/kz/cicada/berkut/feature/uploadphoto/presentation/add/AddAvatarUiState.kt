package kz.cicada.berkut.feature.uploadphoto.presentation.add

import android.graphics.Bitmap
import android.net.Uri
import androidx.compose.runtime.Stable

@Stable
internal data class AddAvatarUiState(
    val avatar: Bitmap? = null,
    val avatarUri: Uri = Uri.EMPTY,
    val loadingContinueButton: Boolean = false,
)
