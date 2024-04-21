package kz.cicada.berkut.feature.savedlocations.presentation.list

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SaveLocationListLauncher(
    val childId: Int,
) : Parcelable