package am.strongte.hub.auth.presentation.input.password

import am.strongte.hub.auth.presentation.common.ProfileField
import androidx.compose.runtime.Stable

@Stable
internal interface InputPasswordController {

    fun onPasswordFieldChange(password: String, changedFieldIndex: Int)

    fun onPasswordVisibilityChange(changedFieldIndex: Int)

    fun onPrimaryButtonClick()

    fun onNavigateBack()

    fun onInputFieldChange(value: String, field: ProfileField)

}