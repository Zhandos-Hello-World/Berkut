package kz.cicada.berkut.feature.auth.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class VerifyPhoneRequest(
    val code: String,
    @SerialName("phone_number") val phoneNumber: String,
    val username: String,
    val role: String = "PARENT"
)