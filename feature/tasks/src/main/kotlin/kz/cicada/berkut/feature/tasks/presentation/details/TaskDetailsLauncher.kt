package kz.cicada.berkut.feature.tasks.presentation.details

import kz.cicada.berkut.feature.tasks.domain.model.TaskStatus

data class TaskDetailsLauncher(
    val taskId: Int,
    val taskName: String,
    val taskDescription: String,
    val status: TaskStatus,
    val coins: Int,
)
