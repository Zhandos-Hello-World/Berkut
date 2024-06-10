package kz.cicada.berkut.feature.savedlocations.data.repository

import kotlinx.coroutines.withContext
import kz.cicada.berkut.feature.savedlocations.data.model.SavedLocationResponse
import kz.cicada.berkut.feature.savedlocations.data.model.SavedLocationsRequest
import kz.cicada.berkut.feature.savedlocations.data.network.SavedLocationsApi
import kz.cicada.berkut.feature.savedlocations.domain.repository.SavedLocationsRepository
import kotlin.coroutines.CoroutineContext

class SavedLocationsRepositoryImpl(
    private val ioDispatcher: CoroutineContext,
    private val apiService: SavedLocationsApi,
) : SavedLocationsRepository {

    override suspend fun saveLocations(
        parentId: Int,
        latitude: Double,
        longitude: Double,
        name: String,
        radius: Double,
        notify: Boolean,
        address: String
    ) {
        withContext(ioDispatcher) {
            apiService.saveLocations(
                parentId = parentId,
                request = SavedLocationsRequest(
                    latitude,
                    longitude,
                    name,
                    radius,
                    notify,
                    address
                ),
            )
        }
    }

    override suspend fun getSaveLocations(childId: Int): List<SavedLocationResponse> {
        return withContext(ioDispatcher) {
            apiService.getSavedLocations(
                childId,
            )
        }
    }

    override suspend fun deleteLocation(
        userId: Int,
        locationId: Int,
    ) {
        return withContext(ioDispatcher) {
            apiService.deleteSavedLocation(
                userId = userId,
                locationId = locationId,
            )
        }
    }
}