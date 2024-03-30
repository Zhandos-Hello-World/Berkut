package kz.cicada.berkut.feature.uploadphoto.data.netowork

import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Part
import retrofit2.http.PartMap
import retrofit2.http.Path

internal interface PhotoApi {

    @Multipart
    @POST("/users/{id}/setImage")
    @JvmSuppressWildcards
    fun uploadPhoto(
        @Part username: RequestBody,
        @Part image: MultipartBody.Part,
    ) : Response<Boolean>

    @Multipart
    @POST("/users/{id}/setImage")
    @JvmSuppressWildcards
    suspend fun register(
        @PartMap username : Map<String, RequestBody>,
        @Part image: MultipartBody.Part,
    ): Response<Boolean>
}