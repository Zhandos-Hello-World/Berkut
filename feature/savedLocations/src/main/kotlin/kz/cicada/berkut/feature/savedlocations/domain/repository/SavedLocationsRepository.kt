package kz.cicada.berkut.feature.savedlocations.domain.repository

import kz.cicada.berkut.feature.savedlocations.data.model.SavedLocationResponse

interface SavedLocationsRepository {

    suspend fun saveLocations(
        parentId: Int,
        latitude: Double,
        longitude: Double,
        name: String,
        radius: Double,
        notify: Boolean,
    )

    suspend fun getSaveLocations(
        childId: Int,
    ) : List<SavedLocationResponse>
}