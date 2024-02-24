package kz.cicada.berkut.lib.core.ui.navigation.cicerone.router

import kz.cicada.berkut.lib.core.ui.navigation.cicerone.BerkutRouter
import kz.cicada.berkut.lib.core.ui.navigation.cicerone.screen.BerkutScreen

class DefaultRouterFacade(
    private val router: BerkutRouter,
) : RouterFacade {

    override fun navigateTo(screen: BerkutScreen) = router.navigateTo(screen)

    override fun newRootScreen(screen: BerkutScreen) = router.newRootScreen(screen)

    override fun replaceScreen(screen: BerkutScreen) = router.replaceScreen(screen)

    override fun backTo(screen: BerkutScreen?) = router.backTo(screen)

    override fun newChain(vararg screens: BerkutScreen) = router.newChain(*screens)

    override fun newRootChain(vararg screens: BerkutScreen) = router.newRootChain(*screens)

    override fun finishChain() = router.finishChain()

    override fun exit() = router.exit()

    override fun backToRootWithChain(vararg screens: BerkutScreen) =
        router.backToRootWithChain(*screens)
}