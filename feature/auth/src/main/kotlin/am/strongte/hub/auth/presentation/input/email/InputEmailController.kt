package am.strongte.hub.auth.presentation.input.email

import am.strongte.hub.auth.presentation.common.ProfileField
import androidx.compose.runtime.Stable

@Stable
internal interface InputEmailController {
    fun onPrimaryButtonClick()
    fun onNavigateBack()
    fun onInputFieldChange(value: String, field: ProfileField)
}