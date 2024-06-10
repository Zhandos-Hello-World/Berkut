package kz.cicada.berkut.feature.savedlocations.data.network

import kz.cicada.berkut.feature.savedlocations.data.model.SavedLocationResponse
import kz.cicada.berkut.feature.savedlocations.data.model.SavedLocationsRequest
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.HTTP
import retrofit2.http.POST
import retrofit2.http.Path

interface SavedLocationsApi {

    @POST("/{parent_id}/saved-locations")
    suspend fun saveLocations(
        @Path("parent_id") parentId: Int,
        @Body request: SavedLocationsRequest,
    )

    @GET("/{child_id}/saved-locations")
    suspend fun getSavedLocations(
        @Path("child_id") childId: Int,
    ): List<SavedLocationResponse>


    @HTTP(
        method = "DELETE",
        path = "/{user_id}/saved-locations/{location_id}",
    )
    suspend fun deleteSavedLocation(
        @Path("user_id") userId: Int,
        @Path("location_id") locationId: Int,
    )
}