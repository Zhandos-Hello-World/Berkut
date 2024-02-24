package am.strongte.hub.auth.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class ValidationEmailRequest(
    @SerialName("email") val email: String,
)