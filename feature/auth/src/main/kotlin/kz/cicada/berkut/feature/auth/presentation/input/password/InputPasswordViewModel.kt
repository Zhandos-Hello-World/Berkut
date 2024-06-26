package kz.cicada.berkut.feature.auth.presentation.input.password

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kz.cicada.berkut.feature.auth.R
import kz.cicada.berkut.feature.auth.navigation.AuthScreens
import kz.cicada.berkut.feature.auth.presentation.common.AuthFlow
import kz.cicada.berkut.feature.auth.presentation.input.email.InputEmailLauncher
import kz.cicada.berkut.feature.auth.presentation.registration.email.RegistrationInputEmailBehavior
import kz.cicada.berkut.feature.auth.presentation.registration.result.RegistrationResultBehavior
import kz.cicada.berkut.feature.auth.presentation.reset.password.ResetPasswordInputEmailBehavior
import kz.cicada.berkut.feature.auth.presentation.reset.password.ResetPasswordResultBehavior
import kz.cicada.berkut.feature.result.presentation.feature.ResultLauncher
import kz.cicada.berkut.feature.result.presentation.navigation.ResultScreens
import kz.cicada.berkut.lib.core.error.handling.ErrorHandler
import kz.cicada.berkut.lib.core.localization.string.VmRes
import kz.cicada.berkut.lib.core.ui.base.BaseViewModel
import kz.cicada.berkut.lib.core.ui.compose.extension.tryToUpdate
import kz.cicada.berkut.lib.core.ui.navigation.cicerone.router.RouterFacade

internal class InputPasswordViewModel(
    private val launcher: InputPasswordLauncher,
    private val errorHandler: ErrorHandler,
    private val routerFacade: RouterFacade,
) : BaseViewModel(), InputPasswordController {

    private val _uiState: MutableStateFlow<InputPasswordUiState> = MutableStateFlow(
        InputPasswordUiState(
            primaryButtonText = launcher.behavior.getPrimaryButtonText(),
            inputItems = launcher.behavior.getInputItems(),
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
                routerFacade.navigateTo(
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
                    )
                )
            },
            onError = {
                // TODO Перехватить ошибки при подключении апи
            },
        )
    }

    override fun onNavigateBack() {
//        routerFacade.backTo(
//            AuthScreens.Email(
//                launcher = InputEmailLauncher(
//                    flow = launcher.flow,
//                    behavior = when (launcher.flow) {
//                        AuthFlow.Registration -> {
//                            RegistrationInputEmailBehavior
//                        }
//
//                        AuthFlow.ResetPassword -> {
//                            ResetPasswordInputEmailBehavior
//                        }
//                    },
//                ),
//            )
//        )
    }

    private fun updateLoadingState(isLoading: Boolean) {
        _uiState.tryToUpdate {
            it.copy(isPrimaryButtonLoading = isLoading)
        }
    }
}