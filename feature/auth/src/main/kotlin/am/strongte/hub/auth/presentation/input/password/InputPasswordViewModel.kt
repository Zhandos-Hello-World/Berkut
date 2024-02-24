package am.strongte.hub.auth.presentation.input.password


import am.strongte.hub.auth.presentation.common.ProfileField
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kz.cicada.berkut.feature.auth.R
import kz.cicada.berkut.lib.core.empty
import kz.cicada.berkut.lib.core.error.handling.ErrorHandler
import kz.cicada.berkut.lib.core.localization.string.VmRes
import kz.cicada.berkut.lib.core.ui.base.BaseViewModel
import kz.cicada.berkut.lib.core.ui.compose.extension.tryToUpdate

internal class InputPasswordViewModel(
    private val launcher: InputPasswordLauncher,
    private val errorHandler: ErrorHandler,
) : BaseViewModel(), InputPasswordController {

    private val _uiState: MutableStateFlow<InputPasswordUiState> = MutableStateFlow(
        InputPasswordUiState(
            primaryButtonText = launcher.behavior.getPrimaryButtonText(),
            inputItems = launcher.behavior.getInputItems(),
            userName = String.empty,
            warnings = listOf(
                VmRes.StrRes(R.string.at_least_8_characters),
                VmRes.StrRes(R.string.at_least_one_uppercase_and_one_lowercase_latin_letter),
                VmRes.StrRes(R.string.at_least_one_number),
                VmRes.StrRes(R.string.at_least_one_special_character_from_the_list),
            ),
        ),
    )

    val uiState: StateFlow<InputPasswordUiState> = _uiState

    override fun onPasswordFieldChange(password: String, changedFieldIndex: Int) {
        _uiState.tryToUpdate {
            it.copy(
                inputItems = it.inputItems.mapIndexed { index, item ->
                    when (index) {
                        changedFieldIndex -> {
                            if (password.length - item.value.length > 1) {
                                return@mapIndexed item
                            }
                            item.copy(value = password.trim())
                        }

                        else -> item
                    }
                },
            )
        }
    }

    override fun onPasswordVisibilityChange(changedFieldIndex: Int) {
        _uiState.tryToUpdate {
            it.copy(
                inputItems = it.inputItems.mapIndexed { index, item ->
                    when (index) {
                        changedFieldIndex -> {
                            item.copy(isPasswordVisible = !item.isPasswordVisible)
                        }

                        else -> item
                    }
                },
            )
        }
    }

    override fun onPrimaryButtonClick() {
        if (_uiState.value.isPrimaryButtonLoading) return

        dataRequest(
            shouldUseBasicErrorHandler = false,
            loading = { isLoading ->
                updateLoadingState(isLoading = isLoading)
            },
            request = {
                // TODO Добавить вызов Behavior
            },
            onSuccess = {
                sendEvent(
//                    OpenScreenEvent(
//                        ResultScreen(
//                            launcher = ResultLauncher(
//                                behavior = when (launcher.flow) {
//                                    AuthFlow.Registration -> {
//                                        RegistrationResultBehavior()
//                                    }
//
//                                    AuthFlow.ResetPassword -> {
//                                        ResetPasswordResultBehavior()
//                                    }
//                                },
//                            ),
//                        ),
//                    ),
                )
            },
            onError = {
                // TODO Перехватить ошибки при подключении апи
            },
        )
    }

    override fun onNavigateBack() {
        sendEvent(
//            NavigateBackUntilScreenEvent(
//                InputEmailScreen(
//                    launcher = InputEmailLauncher(
//                        flow = launcher.flow,
//                        behavior = when (launcher.flow) {
//                            AuthFlow.Registration -> {
//                                RegistrationInputEmailBehavior
//                            }
//
//                            AuthFlow.ResetPassword -> {
//                                ResetPasswordInputEmailBehavior
//                            }
//                        },
//                    ),
//                ),
//            ),
        )
    }

    override fun onInputFieldChange(value: String, field: ProfileField) {
        _uiState.tryToUpdate {
            it.copy(userName = value)
        }
    }

    private fun updateLoadingState(isLoading: Boolean) {
        _uiState.tryToUpdate {
            it.copy(isPrimaryButtonLoading = isLoading)
        }
    }
}