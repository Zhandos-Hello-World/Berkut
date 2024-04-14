package kz.cicada.berkut.feature.uploadphoto.data.netowork

import okhttp3.MultipartBody
import retrofit2.http.Multipart
import retrofit2.http.PUT
import retrofit2.http.Part
import retrofit2.http.Path
import retrofit2.http.Query

internal interface PhotoApi {

    @Multipart
    @PUT("/users/{id}/updateProfile")
    suspend fun uploadPhoto(
        @Path("id") id: Int,
        @Query("username") username: String,
        @Part image: MultipartBody.Part,
    )
}