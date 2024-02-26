package kz.cicada.berkut.feature.auth.presentation.name

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kz.cicada.berkut.feature.auth.R
import kz.cicada.berkut.feature.auth.navigation.AuthScreens
import kz.cicada.berkut.feature.auth.presentation.common.AuthFlow
import kz.cicada.berkut.feature.auth.presentation.input.email.InputEmailLauncher
import kz.cicada.berkut.feature.auth.presentation.registration.email.RegistrationInputEmailBehavior
import kz.cicada.berkut.lib.core.empty
import kz.cicada.berkut.lib.core.localization.string.VmRes
import kz.cicada.berkut.lib.core.ui.base.BaseViewModel
import kz.cicada.berkut.lib.core.ui.compose.extension.tryToUpdate
import kz.cicada.berkut.lib.core.ui.navigation.cicerone.router.RouterFacade

internal class InputNameViewModel(
    private val launcher: InputNameLauncher,
    private val routerFacade: RouterFacade,
) : BaseViewModel(), InputNameController {

    private val _uiState: MutableStateFlow<InputNameUiState> = MutableStateFlow(
        InputNameUiState(
            primaryButtonText = launcher.behavior.getPrimaryButtonText(),
            username = String.empty,
            warnings = listOf(
                VmRes.StrRes(R.string.at_least_one_uppercase_and_one_lowercase_latin_letter),
            ),
        ),
    )
    val uiState: StateFlow<InputNameUiState> = _uiState

    override fun onNameFieldChanged(username: String) {
        _uiState.tryToUpdate {
            it.copy(username = username)
        }
    }

    override fun onPrimaryButtonClick() {
        if (!check(uiState.value.username)) {
            return
        }
        routerFacade.navigateTo(
            AuthScreens.Email(
                InputEmailLauncher(
                    flow = AuthFlow.Registration,
                    behavior = RegistrationInputEmailBehavior,
                )
            )
        )
    }

    override fun onNavigateBack() {
        routerFacade.exit()
    }

    private fun check(username: String): Boolean {
        return username.isNotEmpty()
    }
}