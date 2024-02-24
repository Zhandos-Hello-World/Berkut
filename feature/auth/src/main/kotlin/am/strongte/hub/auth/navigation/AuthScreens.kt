package am.strongte.hub.auth.navigation

import am.strongte.hub.auth.presentation.code.InputCodeFragment
import am.strongte.hub.auth.presentation.code.InputCodeLauncher
import am.strongte.hub.auth.presentation.input.email.InputEmailFragment
import am.strongte.hub.auth.presentation.input.email.InputEmailLauncher
import am.strongte.hub.auth.presentation.input.password.InputPasswordFragment
import am.strongte.hub.auth.presentation.input.password.InputPasswordLauncher
import am.strongte.hub.auth.presentation.login.LoginFragment
import android.os.Parcelable.Creator
import kz.cicada.berkut.lib.core.ui.navigation.cicerone.screen.BerkutFragmentScreen

object AuthScreens {
    private fun InputCode(launcher: InputCodeLauncher): BerkutFragmentScreen = BerkutFragmentScreen {
        InputCodeFragment(
            launcher = launcher)
    }

    fun Login(): BerkutFragmentScreen = BerkutFragmentScreen {
        LoginFragment()
    }

    private fun Email(launcher: InputEmailLauncher): BerkutFragmentScreen = BerkutFragmentScreen {
        InputEmailFragment(launcher = launcher)
    }

    private fun Password(launcher: InputPasswordLauncher): BerkutFragmentScreen = BerkutFragmentScreen {
        InputPasswordFragment(launcher = launcher)
    }
}