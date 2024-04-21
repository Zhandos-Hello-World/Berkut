package kz.cicada.berkut.feature.tasks.data.repository

import kotlinx.coroutines.withContext
import kz.cicada.berkut.feature.tasks.data.mapper.TaskMapper
import kz.cicada.berkut.feature.tasks.data.model.TaskDtoRequest
import kz.cicada.berkut.feature.tasks.data.network.TaskApiService
import kz.cicada.berkut.feature.tasks.domain.model.Task
import kz.cicada.berkut.feature.tasks.domain.model.TaskStatus
import kz.cicada.berkut.feature.tasks.domain.repository.TaskRepository
import kotlin.coroutines.CoroutineContext

class TaskRepositoryImpl(
    private val apiService: TaskApiService,
    private val ioDispatcher: CoroutineContext,
    private val mapper: TaskMapper,
) : TaskRepository {

    override suspend fun addTask(
        name: String,
        description: String,
        coins: Int,
        childId: Int,
    ) {
        return withContext(ioDispatcher) {
            apiService.addTask(
                childId = childId,
                request = TaskDtoRequest(
                    name = name,
                    description = description,
                    coins = coins,
                    status = TaskStatus.NEW.name,
                ),
            )
        }
    }

    override suspend fun deleteTask(
        taskId: Int,
        childId: Int,
    ) {
        return withContext(ioDispatcher) {
            apiService.deleteTask(
                childId = childId,
                taskId = taskId,
            )
        }
    }

    override suspend fun getTasks(childId: Int): List<Task> {
        return withContext(ioDispatcher) {
            apiService.getTasks(
                childId = childId,
            ).map(mapper::map)
        }
    }

    override suspend fun changeStatus(taskId: Int, status: TaskStatus) {
        return withContext(ioDispatcher) {
            apiService.changeStatus(
                taskId = taskId,
                status = status.name,
            )
        }
    }
}