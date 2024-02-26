package kz.cicada.berkut.feature.auth.navigation

import kz.cicada.berkut.feature.auth.presentation.code.InputCodeFragment
import kz.cicada.berkut.feature.auth.presentation.code.InputCodeLauncher
import kz.cicada.berkut.feature.auth.presentation.input.email.InputEmailFragment
import kz.cicada.berkut.feature.auth.presentation.input.email.InputEmailLauncher
import kz.cicada.berkut.feature.auth.presentation.input.password.InputPasswordFragment
import kz.cicada.berkut.feature.auth.presentation.input.password.InputPasswordLauncher
import kz.cicada.berkut.feature.auth.presentation.login.LoginFragment
import kz.cicada.berkut.feature.auth.presentation.name.InputNameFragment
import kz.cicada.berkut.feature.auth.presentation.name.InputNameLauncher
import kz.cicada.berkut.lib.core.ui.navigation.cicerone.screen.BerkutFragmentScreen

object AuthScreens {


    fun Login(): BerkutFragmentScreen = BerkutFragmentScreen {
        LoginFragment()
    }

    internal fun InputName(launcher: InputNameLauncher): BerkutFragmentScreen = BerkutFragmentScreen {
        InputNameFragment(
            launcher = launcher
        )
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