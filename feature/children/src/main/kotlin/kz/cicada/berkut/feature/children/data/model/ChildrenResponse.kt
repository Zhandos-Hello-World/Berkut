package kz.cicada.berkut.feature.children.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kz.cicada.berkut.lib.core.data.network.UserType

@Serializable
data class ChildrenResponse(
    @SerialName("username")
    val username: String,
    @SerialName("role")
    val role: String? = UserType.CHILD.name,
    @SerialName("id")
    val userID: Int,
    @SerialName("phoneNumber")
    val phoneNumber: String,
    @SerialName("coins")
    val coins: Int,
    @SerialName("imageLink")
    val imageLink: String,
)