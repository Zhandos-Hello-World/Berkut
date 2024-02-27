package kz.cicada.berkut.feature.auth.data.remote

import kz.cicada.berkut.feature.auth.data.remote.dto.LoginRequest
import kz.cicada.berkut.feature.auth.data.remote.dto.LoginResponse
import retrofit2.http.Body
import retrofit2.http.POST

internal interface AuthApi {
    @POST("/otc/sign-in")
    suspend fun loginUser(@Body loginRequest: LoginRequest): LoginResponse
}