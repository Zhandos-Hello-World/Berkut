package am.strongte.hub.auth.presentation.code

import am.strongte.hub.auth.domain.repository.ValidationRepository
import am.strongte.hub.auth.presentation.code.InputCodeConstants.DEFAULT_SECONDS_LEFT
import am.strongte.hub.auth.presentation.code.InputCodeConstants.MAX_CODE_NUMBERS
import am.strongte.hub.auth.presentation.code.InputCodeConstants.ONLY_NUMBERS_REGEX
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kz.cicada.berkut.feature.auth.R
import kz.cicada.berkut.lib.core.empty
import kz.cicada.berkut.lib.core.error.handling.ErrorHandler
import kz.cicada.berkut.lib.core.error.handling.INVALID_CODE
import kz.cicada.berkut.lib.core.error.handling.ServerException
import kz.cicada.berkut.lib.core.localization.string.VmRes
import kz.cicada.berkut.lib.core.ui.base.BaseViewModel
import kz.cicada.berkut.lib.core.ui.compose.extension.tryToUpdate

internal class InputCodeViewModel(
    private val launcher: InputCodeLauncher,
    private val errorHandler: ErrorHandler,
    private val repository: ValidationRepository,
) : BaseViewModel(), InputCodeController {

    private val _uiState: MutableStateFlow<InputCodeUiState> = MutableStateFlow(
        InputCodeUiState(
            otpValue = String.empty,
            secondsLeft = DEFAULT_SECONDS_LEFT,
        ),
    )
    val uiState: StateFlow<InputCodeUiState> = _uiState.asStateFlow()
    private var errorJob: Job? = null

    init {
        startTimer()
    }

    override fun onInputCodeFieldChange(otpValue: String) {
        if (otpValue.length <= MAX_CODE_NUMBERS && isNumeric(otpValue)) {
            errorJob?.cancel()
            _uiState.tryToUpdate {
                it.copy(
                    otpValue = otpValue,
                    codeError = null,
                )
            }
        }
    }

    override fun onSendAgainClick() {
        if (_uiState.value.canSendAgain.not()) return
        startTimer()
    }

    override fun onConfirmClick() {
        if (_uiState.value.isConfirmButtonLoading) return

        if (_uiState.value.otpValue.length < MAX_CODE_NUMBERS) {
            onError(errorText = VmRes.StrRes(R.string.input_confirmation_code))
            return
        }

        dataRequest(
            shouldUseBasicErrorHandler = false,
            loading = { isLoading ->
                _uiState.tryToUpdate {
                    it.copy(isConfirmButtonLoading = isLoading)
                }
            },
            request = {
                onError(errorText = null)
                repository.validateCode(
                    email = launcher.email,
                    otpCode = uiState.value.otpValue,
                )
            },
            onSuccess = {
                sendEvent(
//                    OpenScreenEvent(
//                        InputPasswordScreen(
//                            launcher = InputPasswordLauncher(
//                                flow = launcher.flow,
//                                behavior = when (launcher.flow) {
//                                    AuthFlow.ResetPassword -> {
//                                        ResetPasswordInputPasswordBehavior()
//                                    }
//                                    AuthFlow.Registration -> {
//                                        RegistrationInputPasswordBehavior()
//
//                                    }
//                                },
//                            ),
//                        ),
//                    ),
                )
            },
            onError = ::handleOtpError,
        )
    }

    override fun onNavigateBack() {
//        sendEvent(NavigateBackEvent)
    }

    private fun handleOtpError(error: Throwable) {
        when {
            error is ServerException && error.status == INVALID_CODE -> {
                onError(
                    errorText = VmRes.StrRes(R.string.invalid_code),
                )
            }

            else -> errorHandler.handleError(throwable = error, showError = true)
        }
    }

    private fun onError(errorText: VmRes<CharSequence>? = null) {
        _uiState.tryToUpdate {
            it.copy(codeError = errorText)
        }
    }

    private fun startTimer() {
        dataRequest(
            request = {
                _uiState.tryToUpdate {
                    it.copy(
                        secondsLeft = DEFAULT_SECONDS_LEFT,
                        canSendAgain = false,
                    )
                }
                repeat(DEFAULT_SECONDS_LEFT) {
                    delay(1000L)
                    _uiState.tryToUpdate {
                        it.copy(secondsLeft = it.secondsLeft - 1)
                    }
                }
            },
            onSuccess = {
                _uiState.tryToUpdate {
                    it.copy(canSendAgain = true)
                }
            },
        )
    }

    private fun isNumeric(toCheck: String): Boolean {
        val regex = ONLY_NUMBERS_REGEX.toRegex()
        return toCheck.matches(regex)
    }
}