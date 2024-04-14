package kz.cicada.berkut.feature.tasks.presentation.add

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class AddTaskLauncher(
    val childId: Int,
) : Parcelable