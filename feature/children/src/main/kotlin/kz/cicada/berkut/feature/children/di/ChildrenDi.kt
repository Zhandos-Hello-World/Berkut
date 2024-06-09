package kz.cicada.berkut.feature.children.di

import kotlinx.coroutines.Dispatchers
import kz.cicada.berkut.feature.children.data.network.ChildrenApi
import kz.cicada.berkut.feature.children.data.repository.ChildrenRepositoryImpl
import kz.cicada.berkut.feature.children.domain.repository.ChildrenRepository
import kz.cicada.berkut.feature.children.presentation.childs.ChildrenLauncher
import kz.cicada.berkut.feature.children.presentation.childs.ChildrenViewModel
import kz.cicada.berkut.feature.children.presentation.details.ChildDetailsLauncher
import kz.cicada.berkut.feature.children.presentation.details.ChildDetailsViewModel
import kz.cicada.berkut.feature.children.presentation.operation.ChildrenOperationLauncher
import kz.cicada.berkut.feature.children.presentation.operation.ChildrenOperationViewModel
import kz.cicada.berkut.lib.core.data.network.NetworkApiFactory
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val childrenDi = module {
    single {
        get<NetworkApiFactory>().createAuthorizedApi<ChildrenApi>()
    }

    single<ChildrenRepository> {
        ChildrenRepositoryImpl(
            ioDispatcher = Dispatchers.IO,
            api = get(),
        )
    }

    viewModel { (launcher: ChildrenLauncher) ->
        ChildrenViewModel(
            launcher = launcher,
            userPreferences = get(),
            repository = get(),
        )
    }

    viewModel { (launcher: ChildDetailsLauncher) ->
        ChildDetailsViewModel(
            launcher = launcher,
            repo = get(),
        )
    }

    viewModel { (launcher: ChildrenOperationLauncher) ->
        ChildrenOperationViewModel(
            launcher = launcher,
        )
    }
}