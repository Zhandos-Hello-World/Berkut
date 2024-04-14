package kz.cicada.berkut.feature.tasks.domain.model

data class Task(
    val id: Int,
    val name: String,
    val description: String,
    val coins: Int,
    val status: TaskStatus,
)