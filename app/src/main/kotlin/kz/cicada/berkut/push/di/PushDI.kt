package kz.cicada.berkut.push.di

import kotlinx.coroutines.Dispatchers
import kz.cicada.berkut.lib.core.data.network.NetworkApiFactory
import kz.cicada.berkut.push.data.network.FcmApi
import kz.cicada.berkut.push.data.repository.PushRepositoryImpl
import kz.cicada.berkut.push.domain.repository.PushRepository
import kz.cicada.berkut.push.presentation.PushNotificationViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val pushDI = module {

    single<FcmApi> {
        get<NetworkApiFactory>().createAuthorizedApi<FcmApi>()
    }

    single<PushRepository> {
        PushRepositoryImpl(
            ioDispatcher = Dispatchers.IO,
            fcmApi = get(),
            userPreferences = get(),
        )
    }

    viewModel {
        PushNotificationViewModel(
            repo = get(),
        )
    }
}