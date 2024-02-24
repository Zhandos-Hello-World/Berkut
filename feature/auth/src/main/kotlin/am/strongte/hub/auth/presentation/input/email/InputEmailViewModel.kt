package am.strongte.hub.auth.presentation.input.email

import am.strongte.hub.auth.presentation.common.KZ_OR_RU_PHONE_LENGTH
import am.strongte.hub.auth.presentation.common.ProfileField
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kz.cicada.berkut.feature.auth.R
import kz.cicada.berkut.lib.core.empty
import kz.cicada.berkut.lib.core.error.handling.ErrorHandler
import kz.cicada.berkut.lib.core.error.handling.ServerException
import kz.cicada.berkut.lib.core.localization.string.VmRes
import kz.cicada.berkut.lib.core.localization.string.toVmResStr
import kz.cicada.berkut.lib.core.ui.base.BaseViewModel
import kz.cicada.berkut.lib.core.ui.compose.extension.tryToUpdate


internal class InputEmailViewModel(
    private val launcher: InputEmailLauncher,
    private val errorHandler: ErrorHandler,
) : BaseViewModel(), InputEmailController {

    private val _uiState: MutableStateFlow<InputEmailUiState> = MutableStateFlow(
        InputEmailUiState(
            title = launcher.behavior.getTitle(),
            description = launcher.behavior.getDescription(),
            primaryButtonText = launcher.behavior.getPrimaryButtonText(),
            phoneNumber = String.empty,
            userName = String.empty,
        ),
    )

    val uiState: StateFlow<InputEmailUiState> = _uiState

    override fun onPrimaryButtonClick() {
        if (_uiState.value.isPrimaryButtonLoading) return

        if (!validateField()) return

        dataRequest(
            shouldUseBasicErrorHandler = false,
            loading = { isLoading ->
                updateLoadingState(isLoading = isLoading)
            },
            request = {
                launcher.behavior.onPrimaryButtonClick(uiState.value.phoneNumber)
            },
            onSuccess = { events ->
//                sendEvents(events = events)
            },
            onError = { ex ->
                when (ex) {
                    is ServerException -> {
                        updateTextError(
                            textError = R.string.incorrect_email.toVmResStr(),
                        )
                    }

                    else -> {
                        errorHandler.handleError(
                            throwable = ex,
                            showError = true,
                        )
                    }
                }
            },
        )
    }

    override fun onNavigateBack() {
//        sendEvent(NavigateBackEvent)
    }

    override fun onInputFieldChange(value: String, field: ProfileField) {
        when (field) {
            ProfileField.PHONE_NUMBER -> onPhoneChanged(value = value)
            ProfileField.NAME -> { }
            else -> Unit
        }
    }

    private fun onPhoneChanged(value: String) {
        val maxLength = KZ_OR_RU_PHONE_LENGTH
        getUiStateData().let { data ->
            _uiState.update {
                data.copy(
                    phoneNumber = value.take(maxLength),
                    phoneNumberError = null,
                )
            }
        }
    }

    private fun getUiStateData(): InputEmailUiState {
        return uiState.value
    }

    private fun updateTextError(textError: VmRes<CharSequence>) {
        _uiState.tryToUpdate {
            it.copy(textError = textError)
        }
    }

    private fun updateLoadingState(isLoading: Boolean) {
        _uiState.tryToUpdate {
            it.copy(isPrimaryButtonLoading = isLoading)
        }
    }

    private fun validateField(): Boolean {
        val value = _uiState.value.phoneNumber
        if (value.isNotBlank()) return true

        updateTextError(
            textError = VmRes.StrRes(R.string.enter_email_address),
        )
        return false
    }
}