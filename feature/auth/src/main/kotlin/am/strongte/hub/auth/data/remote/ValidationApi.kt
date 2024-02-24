package am.strongte.hub.auth.data.remote

import am.strongte.hub.auth.data.remote.dto.OtpRequest
import am.strongte.hub.auth.data.remote.dto.OtpResponse
import am.strongte.hub.auth.data.remote.dto.RegisterUserRequest
import am.strongte.hub.auth.data.remote.dto.ValidateEmailResponse
import am.strongte.hub.auth.data.remote.dto.ValidationEmailRequest
import am.strongte.hub.auth.data.remote.dto.VerifyPhoneRequest
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Query
import retrofit2.http.QueryMap

internal interface ValidationApi {

    @POST("/api/v1/validate/email")
    suspend fun validateEmail(@Body validationRequest: ValidationEmailRequest): ValidateEmailResponse

    @POST("/api/v1/validate/code")
    suspend fun validateCode(@Body validationRequest: OtpRequest): OtpResponse

    @POST("/auth/generate")
    suspend fun validatePhoneNumber(
        @Query("phone_number") phoneNumber: String,
    )

    @POST("/auth/verify")
    suspend fun verifyPhoneNumber(
        @QueryMap request: VerifyPhoneRequest,
    )

    @POST("/auth/register")
    suspend fun register(
        @QueryMap request: RegisterUserRequest,
    )
}