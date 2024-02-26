package kz.cicada.berkut.feature.auth.presentation.code

interface InputCodeController {

    fun onInputCodeFieldChange(otpValue: String)

    fun onSendAgainClick()

    fun onConfirmClick()

    fun onNavigateBack()
}