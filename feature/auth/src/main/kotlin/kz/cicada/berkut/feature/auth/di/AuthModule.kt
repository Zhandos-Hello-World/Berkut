package kz.cicada.berkut.feature.auth.di

import kotlinx.coroutines.Dispatchers
import kz.cicada.berkut.feature.auth.data.remote.AuthApi
import kz.cicada.berkut.feature.auth.data.remote.ValidationApi
import kz.cicada.berkut.feature.auth.data.repository.DefaultAuthRepository
import kz.cicada.berkut.feature.auth.data.repository.DefaultValidationRepository
import kz.cicada.berkut.feature.auth.domain.repository.AuthRepository
import kz.cicada.berkut.feature.auth.domain.repository.ValidationRepository
import kz.cicada.berkut.feature.auth.presentation.code.InputCodeLauncher
import kz.cicada.berkut.feature.auth.presentation.code.InputCodeViewModel
import kz.cicada.berkut.feature.auth.presentation.input.email.InputEmailLauncher
import kz.cicada.berkut.feature.auth.presentation.input.email.InputEmailViewModel
import kz.cicada.berkut.feature.auth.presentation.input.password.InputPasswordLauncher
import kz.cicada.berkut.feature.auth.presentation.input.password.InputPasswordViewModel
import kz.cicada.berkut.feature.auth.presentation.login.LoginViewModel
import kz.cicada.berkut.feature.auth.presentation.name.InputNameViewModel
import kz.cicada.berkut.lib.core.data.network.NetworkApiFactory
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val authModule = module {
    single {
        get<NetworkApiFactory>().createUnauthorizedApi<AuthApi>()
    }

    single {
        get<NetworkApiFactory>().createUnauthorizedApi<ValidationApi>()
    }

    single<AuthRepository> {
        DefaultAuthRepository(
            api = get(),
            userPref = get(),
            dispatcher = Dispatchers.IO,
        )
    }

    single<ValidationRepository> {
        DefaultValidationRepository(
            api = get(),
            dispatcher = Dispatchers.IO,
        )
    }

    // presentation
    viewModel {
        LoginViewModel(
            repository = get(),
            errorHandler = get(),
            routerFacade = get(),
        )
    }

    viewModel { (launcher: InputCodeLauncher) ->
        InputCodeViewModel(
            launcher = launcher,
            errorHandler = get(),
            authRepo = get(),
            routerFacade = get(),
        )
    }

    viewModel { (launcher: InputEmailLauncher) ->
        InputEmailViewModel(
            launcher = launcher,
            errorHandler = get(),
            routerFacade = get(),
        )
    }

    viewModel { (launcher: InputPasswordLauncher) ->
        InputPasswordViewModel(
            launcher = launcher,
            errorHandler = get(),
            routerFacade = get(),
        )
    }
    viewModel {
        InputNameViewModel(
            launcher = get(),
            routerFacade = get(),
        )
    }

    viewModelOf(::InputEmailViewModel)
}