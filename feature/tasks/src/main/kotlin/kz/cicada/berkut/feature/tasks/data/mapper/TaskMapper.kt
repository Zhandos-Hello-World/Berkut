package kz.cicada.berkut.feature.tasks.data.mapper

import kz.cicada.berkut.feature.tasks.data.model.TaskDtoResponse
import kz.cicada.berkut.feature.tasks.domain.model.Task
import kz.cicada.berkut.feature.tasks.domain.model.TaskStatus

class TaskMapper {

    fun map(from: TaskDtoResponse) : Task {
        return Task(
            id = from.id,
            name = from.name,
            description = from.description,
            coins = from.coins,
            status = TaskStatus.find(from.status)
        )
    }
}