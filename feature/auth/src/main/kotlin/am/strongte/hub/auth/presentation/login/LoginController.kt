package am.strongte.hub.auth.presentation.login

import androidx.compose.runtime.Stable

@Stable
internal interface LoginController {
    fun onEmailFieldChange(email: String)
    fun onChangeFocus(focusedField: FocusedField)
    fun changePasswordVisibility()
    fun onPasswordFieldChange(password: String)
    fun onResetPasswordButtonClick()
    fun onLoginButtonClick()
    fun onRegisterButtonClick()
}