package kz.cicada.berkut.feature.location.data.location

import kz.cicada.berkut.feature.location.data.model.PushLocationRequest
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.QueryMap

interface LocationApi {

    @POST("/child-geo")
    suspend fun pushLocation(
        @Body request: PushLocationRequest,
    )
}