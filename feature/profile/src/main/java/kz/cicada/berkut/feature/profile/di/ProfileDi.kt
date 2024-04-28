package kz.cicada.berkut.feature.profile.di

import kotlinx.coroutines.Dispatchers
import kz.cicada.berkut.feature.profile.data.network.ProfileApi
import kz.cicada.berkut.feature.profile.data.repository.ProfileRepositoryImpl
import kz.cicada.berkut.feature.profile.domain.repository.ProfileRepository
import kz.cicada.berkut.feature.profile.presentation.faq.FAQViewModel
import kz.cicada.berkut.feature.profile.presentation.faq.factory.FAQFactory
import kz.cicada.berkut.feature.profile.presentation.home.HomeViewModel
import kz.cicada.berkut.feature.profile.presentation.logout.LogoutConfirmViewModel
import kz.cicada.berkut.feature.profile.presentation.profile.ProfileViewModel
import kz.cicada.berkut.feature.profile.presentation.support.SupportViewModel
import kz.cicada.berkut.lib.core.data.network.NetworkApiFactory
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val profileDi = module {
    single {
        get<NetworkApiFactory>().createAuthorizedApi<ProfileApi>()
    }

    single<ProfileRepository> {
        ProfileRepositoryImpl(
            ioDispatcher = Dispatchers.IO,
            userPreferences = get(),
            profileApi = get(),
        )
    }

    factory { FAQFactory() }

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

    viewModel {
        ProfileViewModel(
            externalAppService = get(),
            userPreferences = get(),
            repository = get(),
        )
    }

    viewModel {
        FAQViewModel(
            factory = get(),
        )
    }
}