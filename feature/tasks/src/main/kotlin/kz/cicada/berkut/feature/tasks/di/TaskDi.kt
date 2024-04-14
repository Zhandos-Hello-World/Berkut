package kz.cicada.berkut.feature.tasks.di

import kotlinx.coroutines.Dispatchers
import kz.cicada.berkut.feature.tasks.data.mapper.TaskMapper
import kz.cicada.berkut.feature.tasks.data.network.TaskApiService
import kz.cicada.berkut.feature.tasks.data.repository.TaskRepositoryImpl
import kz.cicada.berkut.feature.tasks.domain.repository.TaskRepository
import kz.cicada.berkut.feature.tasks.presentation.add.AddTaskLauncher
import kz.cicada.berkut.feature.tasks.presentation.add.AddTaskViewModel
import kz.cicada.berkut.feature.tasks.presentation.details.TaskDetailsLauncher
import kz.cicada.berkut.feature.tasks.presentation.details.TaskDetailsViewModel
import kz.cicada.berkut.feature.tasks.presentation.list.TaskListLauncher
import kz.cicada.berkut.feature.tasks.presentation.list.TaskListViewModel
import kz.cicada.berkut.lib.core.data.network.NetworkApiFactory
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val taskDI = module {
    single {
        get<NetworkApiFactory>().createAuthorizedApi<TaskApiService>()
    }

    single<TaskRepository> {
        TaskRepositoryImpl(
            apiService = get(),
            ioDispatcher = Dispatchers.IO,
            mapper = get(),
        )
    }

    factory { TaskMapper() }

    viewModel { (launcher: AddTaskLauncher) ->
        AddTaskViewModel(
            launcher = launcher,
            repository = get(),
        )
    }

    viewModel { (launcher: TaskListLauncher) ->
        TaskListViewModel(
            launcher = launcher,
            repository = get(),
            preferences = get(),
        )
    }

    viewModel { (launcher: TaskDetailsLauncher) ->
        TaskDetailsViewModel(
            launcher = launcher,
            repository = get(),
        )
    }
}