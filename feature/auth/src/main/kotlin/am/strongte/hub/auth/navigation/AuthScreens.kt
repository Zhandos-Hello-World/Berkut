package am.strongte.hub.auth.navigation

import am.strongte.hub.auth.presentation.code.InputCodeFragment
import am.strongte.hub.auth.presentation.code.InputCodeLauncher
import am.strongte.hub.auth.presentation.input.email.InputEmailFragment
import am.strongte.hub.auth.presentation.input.email.InputEmailLauncher
import am.strongte.hub.auth.presentation.input.password.InputPasswordFragment
import am.strongte.hub.auth.presentation.input.password.InputPasswordLauncher
import am.strongte.hub.auth.presentation.login.LoginFragment
import kz.cicada.berkut.lib.core.ui.navigation.cicerone.screen.BerkutFragmentScreen

object AuthScreens {

    fun Login(): BerkutFragmentScreen = BerkutFragmentScreen {
        LoginFragment()
    }

    internal fun InputCode(launcher: InputCodeLauncher): BerkutFragmentScreen = BerkutFragmentScreen {
        InputCodeFragment(
            launcher = launcher)
    }

    internal fun Email(launcher: InputEmailLauncher): BerkutFragmentScreen = BerkutFragmentScreen {
        InputEmailFragment(launcher = launcher)
    }

    internal fun Password(launcher: InputPasswordLauncher): BerkutFragmentScreen = BerkutFragmentScreen {
        InputPasswordFragment(launcher = launcher)
    }
}