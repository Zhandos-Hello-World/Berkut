package kz.cicada.berkut.feature.tasks.domain.repository

import kz.cicada.berkut.feature.tasks.domain.model.Task
import kz.cicada.berkut.feature.tasks.domain.model.TaskStatus

interface TaskRepository {

    suspend fun addTask(
        name: String,
        description: String,
        coins: Int,
        childId: Int,
    )

    suspend fun deleteTask(
        taskId: Int,
        childId: Int,
    )

    suspend fun getTasks(
        childId: Int,
    ): List<Task>

    suspend fun changeStatus(
        taskId: Int,
        status: TaskStatus,
    )
}