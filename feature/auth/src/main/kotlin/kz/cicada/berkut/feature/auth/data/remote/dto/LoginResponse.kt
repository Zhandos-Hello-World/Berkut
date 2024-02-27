package kz.cicada.berkut.feature.auth.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LoginResponse(
    @SerialName("jwt") val jwt: String? = null,
    @SerialName("refresh_token") val refreshToken: String? = null,
)