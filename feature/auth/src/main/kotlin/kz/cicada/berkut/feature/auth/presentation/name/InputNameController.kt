package kz.cicada.berkut.feature.auth.presentation.name

import androidx.compose.runtime.Stable

@Stable
internal interface InputNameController {
    fun onNameFieldChanged(username: String)
    fun onPrimaryButtonClick()
    fun onNavigateBack()
}