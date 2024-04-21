package kz.cicada.berkut.feature.sos.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class HotlineNumberResponse(
    @SerialName("phoneNumber")
    val phoneNumber: String,
    @SerialName("name")
    val name: String,
)