package kz.cicada.berkut.feature.sos.data.network

import kz.cicada.berkut.feature.sos.data.model.AddHotlineNumberRequest
import kz.cicada.berkut.feature.sos.data.model.HotlineNumberResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface HotlineNumbersApi {

    @POST("/hotline-numbers")
    suspend fun addHotlineNumbers(
        @Body request: AddHotlineNumberRequest,
    )

    @GET("/hotline-numbers/{child-id}")
    suspend fun getHotlineNumbers(
        @Path("child-id") childId: String
    ) : List<HotlineNumberResponse>
}