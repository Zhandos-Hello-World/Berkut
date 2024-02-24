package kz.cicada.berkut.lib.core.ui.navigation.cicerone.router

import kz.cicada.berkut.lib.core.ui.navigation.cicerone.screen.BerkutScreen

interface RouterFacade {

    fun navigateTo(screen: BerkutScreen)

    fun backTo(screen: BerkutScreen?)

    fun exit()

    fun finishChain()

    fun newChain(vararg screens: BerkutScreen)

    fun newRootChain(vararg screens: BerkutScreen)

    fun newRootScreen(screen: BerkutScreen)

    fun replaceScreen(screen: BerkutScreen)

    fun backToRootWithChain(vararg screens: BerkutScreen)
}