package kz.cicada.berkut.feature.location.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PushLocationRequest(
    @SerialName("userId")
    val userId: Int,
    @SerialName("latitude")
    val latitude: String,
    @SerialName("longitude")
    val longitude: String,
    @SerialName("timestamp")
    val timestamp: String,
    @SerialName("timezone")
    val timeZone: String = "",
    @SerialName("username")
    val username: String,
    @SerialName("battery")
    val battery: Int,
)