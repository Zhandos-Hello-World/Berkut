package kz.cicada.berkut.feature.result.di

import kz.cicada.berkut.feature.result.presentation.feature.ResultViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val resultModule = module {

    viewModel { parameters ->
        ResultViewModel(
            launcher = parameters.get(),
        )
    }

    viewModelOf(::ResultViewModel)

}