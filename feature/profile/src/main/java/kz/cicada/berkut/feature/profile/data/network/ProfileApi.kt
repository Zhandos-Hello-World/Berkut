package kz.cicada.berkut.feature.profile.data.network

import okhttp3.MultipartBody
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.PUT
import retrofit2.http.Part
import retrofit2.http.Path
import retrofit2.http.Query

interface ProfileApi {

    @PUT("/users/{id}/updateProfile")
    suspend fun updateProfileUserName(
        @Path("id") id: Int,
        @Query("username") username: String,
    )

    @Multipart
    @PUT("/users/{id}/updateProfile")
    suspend fun uploadPhoto(
        @Path("id") id: Int,
        @Query("username") username: String,
        @Part image: MultipartBody.Part,
    )

    @GET("/users/{id}/profile-photo")
    suspend fun getProfile(
        @Path("id") id: Int,
    ): String
}