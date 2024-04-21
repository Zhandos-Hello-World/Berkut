package kz.cicada.berkut.feature.savedlocations.presentation.confirm

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class SavedLocationsConfirmLauncher(
    val latitude: Double,
    val longitude: Double,
    val radius: Double,
) : Parcelable