package kz.cicada.berkut.feature.tasks.presentation.details

import androidx.compose.runtime.Stable

@Stable
data class TaskDetailsDataState(
    val taskName: String,
    val taskDescription: String,
    val coins: Int,
)