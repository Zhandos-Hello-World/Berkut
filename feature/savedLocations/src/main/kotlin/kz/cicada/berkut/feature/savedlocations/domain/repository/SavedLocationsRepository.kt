package kz.cicada.berkut.feature.savedlocations.domain.repository

import kz.cicada.berkut.feature.savedlocations.data.model.SavedLocationResponse
import retrofit2.http.Path

interface SavedLocationsRepository {

    suspend fun saveLocations(
        parentId: Int,
        latitude: Double,
        longitude: Double,
        name: String,
        radius: Double,
        notify: Boolean,
        address: String
    )

    suspend fun getSaveLocations(
        childId: Int,
    ) : List<SavedLocationResponse>

    suspend fun deleteLocation(
        userId: Int,
        locationId: Int,
    )
}