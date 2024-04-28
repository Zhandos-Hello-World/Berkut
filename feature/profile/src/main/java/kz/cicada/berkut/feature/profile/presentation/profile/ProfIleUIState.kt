package kz.cicada.berkut.feature.profile.presentation.profile

import android.graphics.Bitmap
import android.net.Uri

sealed interface ProfIleUIState {
    data class Data(
        val userId: Int = 0,
        val username: String = "",
        val phoneNumber: String = "",
        val avatar: Bitmap? = null,
        val avatarUri: Uri = Uri.EMPTY,
        val loadingContinueButton: Boolean = false,
        val enabled: Boolean = false,
    ) : ProfIleUIState
}