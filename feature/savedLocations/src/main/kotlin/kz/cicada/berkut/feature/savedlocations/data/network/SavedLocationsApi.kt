package kz.cicada.berkut.feature.savedlocations.data.network

import kz.cicada.berkut.feature.savedlocations.data.model.SavedLocationResponse
import kz.cicada.berkut.feature.savedlocations.data.model.SavedLocationsRequest
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface SavedLocationsApi {

    @POST("/saved-locations/{parent_id}")
    suspend fun saveLocations(
        @Path("parent_id") parentId: Int,
        @Body request: SavedLocationsRequest,
    )

    @GET("/saved-locations/{child_id}")
    suspend fun getSavedLocations(
        @Path("child_id") childId: Int,
    ): List<SavedLocationResponse>
}