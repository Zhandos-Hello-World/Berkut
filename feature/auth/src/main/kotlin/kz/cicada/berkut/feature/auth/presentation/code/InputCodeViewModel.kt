package kz.cicada.berkut.feature.auth.presentation.code

import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kz.cicada.berkut.feature.auth.R
import kz.cicada.berkut.feature.auth.domain.repository.AuthRepository
import kz.cicada.berkut.feature.auth.presentation.code.InputCodeConstants.DEFAULT_SECONDS_LEFT
import kz.cicada.berkut.feature.auth.presentation.code.InputCodeConstants.MAX_CODE_NUMBERS
import kz.cicada.berkut.feature.auth.presentation.code.InputCodeConstants.ONLY_NUMBERS_REGEX
import kz.cicada.berkut.feature.auth.presentation.common.AuthFlow
import kz.cicada.berkut.feature.auth.presentation.registration.result.RegistrationResultBehavior
import kz.cicada.berkut.feature.auth.presentation.reset.password.ResetPasswordResultBehavior
import kz.cicada.berkut.feature.result.presentation.feature.ResultLauncher
import kz.cicada.berkut.feature.result.presentation.navigation.ResultScreens
import kz.cicada.berkut.feature.uploadphoto.presentation.navigation.AddAvatarScreen
import kz.cicada.berkut.lib.core.empty
import kz.cicada.berkut.lib.core.error.handling.ErrorHandler
import kz.cicada.berkut.lib.core.error.handling.INVALID_CODE
import kz.cicada.berkut.lib.core.error.handling.ServerException
import kz.cicada.berkut.lib.core.localization.string.VmRes
import kz.cicada.berkut.lib.core.ui.base.BaseViewModel
import kz.cicada.berkut.lib.core.ui.compose.extension.tryToUpdate
import kz.cicada.berkut.lib.core.ui.navigation.cicerone.router.RouterFacade

internal class InputCodeViewModel(
    private val launcher: InputCodeLauncher,
    private val errorHandler: ErrorHandler,
    private val authRepo: AuthRepository,
    private val routerFacade: RouterFacade,
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
                authRepo.loginUser(
                    params = launcher.params.copy(
                        code = uiState.value.otpValue,
                    )
                )
            },
            onSuccess = {
                routerFacade.newChain(
                    ResultScreens.Result(
                        launcher = ResultLauncher(
                            behavior = when (launcher.flow) {
                                AuthFlow.Registration -> {
                                    RegistrationResultBehavior()
                                }

                                AuthFlow.ResetPassword -> {
                                    ResetPasswordResultBehavior()
                                }
                            },
                        )
                    ),
                    AddAvatarScreen.AddAvatar(),
                )
            },
            onError = ::handleOtpError,
        )
    }

    override fun onNavigateBack() {
        routerFacade.exit()
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