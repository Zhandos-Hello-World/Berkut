package kz.cicada.berkut.feature.auth.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class OtpRequest(
    @SerialName("email") val email: String,
    @SerialName("code") val code: String,
)