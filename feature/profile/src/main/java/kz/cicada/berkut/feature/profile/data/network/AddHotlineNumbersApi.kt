package kz.cicada.berkut.feature.profile.data.network

import kz.cicada.berkut.feature.profile.data.model.AddHotlineNumberRequest
import retrofit2.http.Body
import retrofit2.http.POST

interface AddHotlineNumbersApi {

    @POST("/hotline-numbers")
    suspend fun addHotlineNumbers(
        @Body request: AddHotlineNumberRequest,
    )
}