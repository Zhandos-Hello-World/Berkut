package kz.cicada.berkut.lib.core.data.error

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal class ErrorResponse(
    @SerialName("code")
    val code: Int? = null,
    @SerialName("status")
    val status: String? = null,
    @SerialName("message")
    val message: String? = null,
)