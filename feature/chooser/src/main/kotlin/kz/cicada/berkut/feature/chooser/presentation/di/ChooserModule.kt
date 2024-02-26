package kz.cicada.berkut.feature.chooser.presentation.di

import kz.cicada.berkut.feature.chooser.presentation.feature.searchable.ui.SearchableChooserViewModel
import kz.cicada.berkut.feature.chooser.presentation.feature.simple.ui.SimpleChooserViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val chooserModule = module {

    viewModel { parameters -> SearchableChooserViewModel(launcher = parameters.get()) }

    viewModel { parameters ->
        SimpleChooserViewModel(
            launcher = parameters.get(),
            routerFacade = get(),
        )
    }

    viewModelOf(::SearchableChooserViewModel)
    viewModelOf(::SimpleChooserViewModel)

}
