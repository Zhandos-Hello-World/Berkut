package kz.cicada.berkut.feature.uploadphoto.di

import kotlinx.coroutines.Dispatchers
import kz.cicada.berkut.feature.uploadphoto.data.netowork.PhotoApi
import kz.cicada.berkut.feature.uploadphoto.data.repository.PhotoRepositoryImpl
import kz.cicada.berkut.feature.uploadphoto.domain.PhotoRepository
import kz.cicada.berkut.feature.uploadphoto.presentation.add.AddAvatarViewModel
import kz.cicada.berkut.lib.core.data.network.NetworkApiFactory
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val addAvatarModule = module {

    single<PhotoApi> {
        get<NetworkApiFactory>().createAuthorizedApi<PhotoApi>()
    }

    single<PhotoRepository> {
        PhotoRepositoryImpl(
            ioDispatcher = Dispatchers.IO,
            photoApi = get(),
            userPreferences = get(),
            context = androidContext(),
        )
    }

    viewModel {
        AddAvatarViewModel(
            externalAppService = get(),
            routerFacade = get(),
            photoRepo = get(),
        )
    }
}