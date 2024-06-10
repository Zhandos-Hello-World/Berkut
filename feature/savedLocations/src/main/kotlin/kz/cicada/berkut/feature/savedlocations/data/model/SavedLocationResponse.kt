package kz.cicada.berkut.feature.savedlocations.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SavedLocationResponse(
    @SerialName("id")
    val id: Int,
    @SerialName("latitude")
    val latitude: Double,
    @SerialName("longitude")
    val longitude: Double,
    @SerialName("name")
    val name: String,
    @SerialName("radius")
    val radius: Double,
    @SerialName("notify")
    val notify: Boolean,
)