package kz.cicada.berkut.feature.savedlocations.di

import kotlinx.coroutines.Dispatchers
import kz.cicada.berkut.feature.savedlocations.data.network.SavedLocationsApi
import kz.cicada.berkut.feature.savedlocations.data.repository.SavedLocationsRepositoryImpl
import kz.cicada.berkut.feature.savedlocations.domain.repository.SavedLocationsRepository
import kz.cicada.berkut.feature.savedlocations.presentation.confirm.SavedLocationsConfirmLauncher
import kz.cicada.berkut.feature.savedlocations.presentation.confirm.SavedLocationsConfirmViewModel
import kz.cicada.berkut.feature.savedlocations.presentation.list.SaveLocationListLauncher
import kz.cicada.berkut.feature.savedlocations.presentation.list.SaveLocationListViewModel
import kz.cicada.berkut.feature.savedlocations.presentation.maps.SavedLocationsMapViewModel
import kz.cicada.berkut.lib.core.data.network.NetworkApiFactory
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val savedLocationsDi = module {

    single {
        get<NetworkApiFactory>().createAuthorizedApi<SavedLocationsApi>()
    }

    single<SavedLocationsRepository> {
        SavedLocationsRepositoryImpl(
            ioDispatcher = Dispatchers.IO,
            apiService = get(),
        )
    }

    viewModel {
        SavedLocationsMapViewModel()
    }

    viewModel {
        SaveLocationListViewModel(
            repository = get(),
            userPreferences = get(),
        )
    }

    viewModel { (launcher: SavedLocationsConfirmLauncher) ->
        SavedLocationsConfirmViewModel(
            launcher = launcher,
            userPref = get(),
            repo = get(),
        )
    }
}