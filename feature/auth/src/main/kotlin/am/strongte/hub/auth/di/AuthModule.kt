package am.strongte.hub.auth.di

import am.strongte.hub.auth.data.remote.AuthApi
import am.strongte.hub.auth.data.remote.ValidationApi
import am.strongte.hub.auth.data.repository.DefaultAuthRepository
import am.strongte.hub.auth.data.repository.DefaultValidationRepository
import am.strongte.hub.auth.domain.repository.AuthRepository
import am.strongte.hub.auth.domain.repository.ValidationRepository
import am.strongte.hub.auth.presentation.code.InputCodeLauncher
import am.strongte.hub.auth.presentation.code.InputCodeViewModel
import am.strongte.hub.auth.presentation.input.email.InputEmailLauncher
import am.strongte.hub.auth.presentation.input.email.InputEmailViewModel
import am.strongte.hub.auth.presentation.input.password.InputPasswordLauncher
import am.strongte.hub.auth.presentation.input.password.InputPasswordViewModel
import am.strongte.hub.auth.presentation.login.LoginViewModel
import kotlinx.coroutines.Dispatchers
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
            repository = get(),
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

    viewModelOf(::InputEmailViewModel)
}