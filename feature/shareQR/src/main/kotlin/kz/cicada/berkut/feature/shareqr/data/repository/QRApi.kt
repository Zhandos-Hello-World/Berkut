package kz.cicada.berkut.feature.shareqr.data.repository

import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface QRApi {

    @POST("/parents/{parent_id}")
    suspend fun addChild(
        @Path("parent_id") parentId: Int,
        @Query("child_id") childId: Int,
    )
}