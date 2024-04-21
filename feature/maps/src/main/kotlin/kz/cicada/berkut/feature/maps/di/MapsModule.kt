package kz.cicada.berkut.feature.maps.di

import kz.cicada.berkut.feature.maps.presentation.MapsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mapsModule = module {

    viewModel {
        MapsViewModel(
            routerFacade = get(),
            preferences = get(),
            savedLocationsRepo = get(),
        )
    }
}