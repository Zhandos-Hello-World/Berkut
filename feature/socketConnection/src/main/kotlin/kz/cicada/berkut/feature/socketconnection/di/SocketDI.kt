package kz.cicada.berkut.feature.socketconnection.di

import kz.cicada.berkut.feature.socketconnection.presentation.SocketLauncher
import kz.cicada.berkut.feature.socketconnection.presentation.SocketViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val socketDi = module {

    viewModel { (launcher: SocketLauncher) ->
        SocketViewModel(
            userPreferences = get(),
            launcher = launcher,
        )
    }
}