package kz.cicada.berkut.feature.auth.presentation.input.email

import androidx.compose.runtime.Stable
import kz.cicada.berkut.feature.auth.presentation.common.ProfileField

@Stable
internal interface InputEmailController {
    fun onPrimaryButtonClick()
    fun onNavigateBack()
    fun onInputFieldChange(value: String, field: ProfileField)
}