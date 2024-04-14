package kz.cicada.berkut.feature.savedlocations.presentation.maps

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SavedLocationsMapLauncher(
    val list: List<SafeLocation>,
) : Parcelable {

    @Parcelize
    data class SafeLocation(
        val name: String,
        val radius: Double,
        val latitude: Double,
        val longitude: Double,
    ) : Parcelable
}