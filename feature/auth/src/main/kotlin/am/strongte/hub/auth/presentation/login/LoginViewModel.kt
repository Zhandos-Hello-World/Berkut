package am.strongte.hub.auth.presentation.login

import am.strongte.hub.auth.domain.repository.AuthRepository
import am.strongte.hub.auth.navigation.AuthScreens
import am.strongte.hub.auth.presentation.common.AuthFlow
import am.strongte.hub.auth.presentation.common.isValidEmail
import am.strongte.hub.auth.presentation.input.email.InputEmailLauncher
import am.strongte.hub.auth.presentation.registration.email.RegistrationInputEmailBehavior
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kz.cicada.berkut.feature.auth.R
import kz.cicada.berkut.lib.core.empty
import kz.cicada.berkut.lib.core.error.handling.BAD_REQUEST
import kz.cicada.berkut.lib.core.error.handling.ErrorHandler
import kz.cicada.berkut.lib.core.error.handling.ServerException
import kz.cicada.berkut.lib.core.error.handling.UnauthorizedException
import kz.cicada.berkut.lib.core.localization.string.VmRes
import kz.cicada.berkut.lib.core.ui.base.BaseViewModel
import kz.cicada.berkut.lib.core.ui.compose.extension.tryToUpdate
import kz.cicada.berkut.lib.core.ui.navigation.cicerone.router.RouterFacade

class LoginViewModel(
    private val repository: AuthRepository,
    private val errorHandler: ErrorHandler,
    private val routerFacade: RouterFacade,
) : BaseViewModel(), LoginController {

    private val _uiState = MutableStateFlow(
        LoginUiState(email = String.empty, password = String.empty),
    )

    internal val uiState: StateFlow<LoginUiState> = _uiState.asStateFlow()

    override fun onEmailFieldChange(email: String) {
        _uiState.tryToUpdate {
            it.copy(email = email, emailError = null)
        }
    }

    override fun onPasswordFieldChange(password: String) {
        _uiState.tryToUpdate {
            it.copy(password = password, passwordError = null)
        }
    }

    override fun onResetPasswordButtonClick() {
//        sendEvent(
//            OpenScreenEvent(
//                InputEmailScreen(
//                    launcher = InputEmailLauncher(
//                        flow = AuthFlow.ResetPassword,
//                        behavior = ResetPasswordInputEmailBehavior,
//                    ),
//                ),
//            ),
//        )
    }

    override fun onLoginButtonClick() {
        val value = _uiState.value

        if (value.isEnterLoading) return

        if (!areFieldsValid()) return

        dataRequest(
            shouldUseBasicErrorHandler = false,
            loading = { isLoading ->
                _uiState.tryToUpdate {
                    it.copy(isEnterLoading = isLoading)
                }
            },
            request = {
                _uiState.tryToUpdate {
                    it.copy(passwordError = null, emailError = null)
                }
                repository.loginUser(
                    email = value.email,
                    password = value.password,
                )
            },
            onSuccess = {
                // TODO: Добавить проверку на первичную и вторичную авторизацию
                if (true) {
                    sendEvent(
//                        OpenNewRootScreenEvent(
//                            BottomNavigableScreen(),
//                        ),
                    )
                } else {
                    sendEvent(
//                        OpenNewRootScreenEvent(
//                            PrefillProfileScreen(
//                                launcher = PrefillProfileLauncher(
//                                    email = _uiState.value.email,
//                                ),
//                            ),
//                        ),
                    )
                }
            },
            onError = ::handleLoginError,
        )
    }

    override fun onRegisterButtonClick() {
        routerFacade.navigateTo(
            AuthScreens.Email(
                InputEmailLauncher(
                    flow = AuthFlow.Registration,
                    behavior = RegistrationInputEmailBehavior,
                )
            )
        )
    }

    override fun onChangeFocus(focusedField: FocusedField) {
        _uiState.tryToUpdate {
            it.copy(focusedField = focusedField)
        }
    }

    override fun changePasswordVisibility() {
        _uiState.tryToUpdate {
            it.copy(isPasswordVisible = !uiState.value.isPasswordVisible)
        }
    }

    private fun areFieldsValid(): Boolean {
        val email = uiState.value.email
        val password = uiState.value.password
        var emailError: VmRes<CharSequence>? = null
        var passwordError: VmRes<CharSequence>? = null
        var allValid = true

        if (email.isBlank()) {
            emailError = VmRes.StrRes(R.string.input_email)
            allValid = false
        } else if (!isValidEmail(email = email)) {
            emailError = VmRes.StrRes(R.string.invalid_email)
            allValid = false
        }

        if (password.isBlank()) {
            passwordError = VmRes.StrRes(R.string.input_password)
            allValid = false
        }

        showErrorOnFields(
            emailError = emailError,
            passwordError = passwordError,
        )

        return allValid
    }

    private fun showErrorOnFields(
        emailError: VmRes<CharSequence>?,
        passwordError: VmRes<CharSequence>?,
    ) {
        _uiState.tryToUpdate {
            it.copy(
                isEnterLoading = false,
                passwordError = passwordError,
                emailError = emailError,
            )
        }
    }

    private fun handleLoginError(error: Throwable) {
        when {
            error is UnauthorizedException -> {
                showErrorOnFields(
                    emailError = VmRes.Str(String.empty),
                    passwordError = VmRes.StrRes(R.string.invalid_login_or_password),
                )
            }

            error is ServerException && error.status == BAD_REQUEST -> {
                showErrorOnFields(
                    emailError = VmRes.Str(String.empty),
                    passwordError = VmRes.StrRes(R.string.invalid_login_or_password),
                )
            }

            else -> errorHandler.handleError(throwable = error, showError = true)
        }
    }
}
