package kz.cicada.berkut.di

import com.github.terrakok.cicerone.Cicerone
import kz.cicada.berkut.lib.core.ui.navigation.cicerone.BerkutRouter
import kz.cicada.berkut.lib.core.ui.navigation.cicerone.router.DefaultRouterFacade
import kz.cicada.berkut.lib.core.ui.navigation.cicerone.router.RouterFacade
import org.koin.dsl.module

internal val appNavigationModule = module {

    single {
       Cicerone.create(customRouter = BerkutRouter())
    }
    single<RouterFacade> {
       DefaultRouterFacade(router = get<Cicerone<BerkutRouter>>().router)
    }
    single {
       get<Cicerone<BerkutRouter>>().getNavigatorHolder()
    }
}