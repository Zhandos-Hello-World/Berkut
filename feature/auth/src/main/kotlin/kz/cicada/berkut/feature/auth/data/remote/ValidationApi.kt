package kz.cicada.berkut.feature.auth.data.remote

import kz.cicada.berkut.feature.auth.data.remote.dto.OtpRequest
import kz.cicada.berkut.feature.auth.data.remote.dto.VerifyPhoneRequest
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.QueryMap

internal interface ValidationApi {

    @POST("/otc/generate")
    suspend fun validatePhoneNumber(
        @QueryMap request: OtpRequest,
    )

    @POST("/otc/sign-in")
    suspend fun verifyPhoneNumber(
        @Body request: VerifyPhoneRequest,
    )
}