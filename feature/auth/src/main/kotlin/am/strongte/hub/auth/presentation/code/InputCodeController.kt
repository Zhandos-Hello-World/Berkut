package am.strongte.hub.auth.presentation.code

interface InputCodeController {

    fun onInputCodeFieldChange(otpValue: String)

    fun onSendAgainClick()

    fun onConfirmClick()

    fun onNavigateBack()
}