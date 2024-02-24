package am.strongte.hub.auth.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class OtpResponse(
    @SerialName("status") val status: String? = null,
)