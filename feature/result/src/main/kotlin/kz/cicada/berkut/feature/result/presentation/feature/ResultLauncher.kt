package kz.cicada.berkut.feature.result.presentation.feature

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ResultLauncher(
    val behavior: ResultBehavior,
) : Parcelable
