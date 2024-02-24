package am.strongte.hub.auth.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class LoginRequest(
    @SerialName("email") val email: String,
    @SerialName("password") val password: String,
)
