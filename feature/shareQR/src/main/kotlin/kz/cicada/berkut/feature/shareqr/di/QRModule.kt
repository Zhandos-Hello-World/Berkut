package kz.cicada.berkut.feature.shareqr.di

import kz.cicada.berkut.feature.shareqr.presentation.ShareQRViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val qrModule = module {

    viewModel {
        ShareQRViewModel(
            routerFacade = get(),
            userPreferences = get(),
        )
    }

}