package kz.cicada.berkut.feature.sos.di

import kz.cicada.berkut.feature.sos.data.network.HotlineNumbersApi
import kz.cicada.berkut.feature.sos.presentation.hotline.AddHotlineNumbersViewModel
import kz.cicada.berkut.feature.sos.presentation.hotline.listHotline.HotlineListViewModel
import kz.cicada.berkut.lib.core.data.network.NetworkApiFactory
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val sosDi = module {

    single {
        get<NetworkApiFactory>().createAuthorizedApi<HotlineNumbersApi>()
    }

    viewModel {
        AddHotlineNumbersViewModel(
            api = get(),
            routerFacade = get(),
        )
    }

    viewModel {
        AddHotlineNumbersViewModel(
            routerFacade = get(),
            api = get(),
        )
    }

    viewModel {
        HotlineListViewModel(
            api = get(),
            userPref = get(),
        )
    }
}