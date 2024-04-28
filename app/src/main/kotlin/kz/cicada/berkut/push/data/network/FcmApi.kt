package kz.cicada.berkut.push.data.network

import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface FcmApi {

    @POST("/users/{id}/fcm-token")
    suspend fun updateToken(
        @Query("fcm-token") fcmToken: String,
        @Path("id") id: Int,
    )
}