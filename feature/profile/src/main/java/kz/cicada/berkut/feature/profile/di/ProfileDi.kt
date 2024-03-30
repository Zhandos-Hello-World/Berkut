package kz.cicada.berkut.feature.profile.di

import kz.cicada.berkut.feature.profile.data.network.AddHotlineNumbersApi
import kz.cicada.berkut.feature.profile.presentation.hotline.AddHotlineNumbersViewModel
import kz.cicada.berkut.feature.profile.presentation.hotline.listHotline.HotlineListViewModel
import kz.cicada.berkut.feature.profile.presentation.home.HomeViewModel
import kz.cicada.berkut.feature.profile.presentation.logout.LogoutConfirmViewModel
import kz.cicada.berkut.feature.profile.presentation.support.SupportViewModel
import kz.cicada.berkut.lib.core.data.network.NetworkApiFactory
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val profileDi = module {
    single {
        get<NetworkApiFactory>().createAuthorizedApi<AddHotlineNumbersApi>()
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
        HotlineListViewModel()
    }

    viewModel {
        HomeViewModel(
            userPreferences = get(),
        )
    }

    viewModel {
        SupportViewModel(
            userPreferences = get(),
        )
    }

    viewModel {
        LogoutConfirmViewModel()
    }
}