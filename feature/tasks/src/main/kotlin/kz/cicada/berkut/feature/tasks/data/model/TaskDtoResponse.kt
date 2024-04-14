package kz.cicada.berkut.feature.tasks.data.model

import kotlinx.serialization.Serializable

@Serializable
data class TaskDtoResponse(
    val id: Int,
    val name: String,
    val description: String,
    val coins: Int,
    val status: String,
)