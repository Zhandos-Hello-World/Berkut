package kz.cicada.berkut.feature.auth.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class LoginRequest(
    @SerialName("username") val username: String,
    @SerialName("code") val code: String,
    @SerialName("role") val role: String,
    @SerialName("phone_number") val phoneNumber: String,
    @SerialName("fcm_token") val deviceId: String,
)
