package kz.cicada.berkut.feature.uploadphoto.di

import kz.cicada.berkut.feature.uploadphoto.presentation.add.AddAvatarViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val addAvatarModule = module {

    viewModel {
        AddAvatarViewModel(
            externalAppService = get(),
            routerFacade = get(),
        )
    }
}