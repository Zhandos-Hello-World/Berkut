package am.strongte.hub.auth.presentation.input.password

import androidx.compose.runtime.Stable

@Stable
internal interface InputPasswordController {

    fun onPasswordFieldChange(password: String, changedFieldIndex: Int)
    fun onPasswordVisibilityChange(changedFieldIndex: Int)
    fun onPrimaryButtonClick()
    fun onNavigateBack()

}