package kz.cicada.berkut.feature.uploadphoto.presentation.add

import androidx.compose.runtime.Stable

@Stable
internal interface AddAvatarController {
    fun onAddAvatarButtonIconClick()
    fun onContinueButtonClick()
    fun onSkipButtonClick()
    fun onNavigateBack()
}
