package kz.cicada.berkut.feature.language.di

import kz.cicada.berkut.feature.language.data.source.languageDataStore
import kz.cicada.berkut.feature.language.data.repository.DefaultLanguageRepository
import kz.cicada.berkut.feature.language.domain.LanguageRepository
import kz.cicada.berkut.feature.language.presentation.onboarding.OnBoardingLanguageViewModel
import kz.cicada.berkut.feature.language.presentation.role.ChooseRoleViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val languageModule = module {

    single<LanguageRepository> {
        DefaultLanguageRepository(
            dataStore = androidContext().languageDataStore,
        )
    }

    viewModel {
        OnBoardingLanguageViewModel(
            repository = get(),
            routerFacade = get(),
        )
    }

    viewModel {
        ChooseRoleViewModel(
            routerFacade = get(),
        )
    }
}
