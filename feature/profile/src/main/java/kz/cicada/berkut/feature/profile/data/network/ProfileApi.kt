package kz.cicada.berkut.feature.profile.data.network

import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface ProfileApi {

    @PUT("/users/{id}/updateProfile")
    suspend fun updateProfile(
        @Path("id") id: Int,
        @Query("username") username: String,
    )
}