package kz.cicada.berkut.feature.shareqr.di

import kz.cicada.berkut.feature.shareqr.data.repository.QRApi
import kz.cicada.berkut.feature.shareqr.presentation.scan.ScanQRViewModel
import kz.cicada.berkut.feature.shareqr.presentation.share.ShareQRViewModel
import kz.cicada.berkut.lib.core.data.network.NetworkApiFactory
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val qrModule = module {

    factory {
        get<NetworkApiFactory>().createAuthorizedApi<QRApi>()
    }

    viewModel {
        ScanQRViewModel(
            api = get(),
            userPref = get(),
            routerFacade = get(),
        )
    }

    viewModel {
        ShareQRViewModel(
            routerFacade = get(),
            userPreferences = get(),
        )
    }
}