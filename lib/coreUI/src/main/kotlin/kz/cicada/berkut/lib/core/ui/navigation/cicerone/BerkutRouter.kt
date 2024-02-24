package kz.cicada.berkut.lib.core.ui.navigation.cicerone

import com.github.terrakok.cicerone.BackTo
import com.github.terrakok.cicerone.Forward
import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.Screen

class BerkutRouter : Router() {

    fun backToRootWithChain(vararg screens: Screen) {
        executeCommands(
            BackTo(null),
            *screens.map(::Forward).toTypedArray(),
        )
    }
}