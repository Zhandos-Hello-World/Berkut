package kz.cicada.berkut.feature.uploadphoto.presentation.add

import android.graphics.Bitmap
import androidx.compose.runtime.Stable

@Stable
internal data class AddAvatarUiState(
    val avatar: Bitmap? = null,
)
