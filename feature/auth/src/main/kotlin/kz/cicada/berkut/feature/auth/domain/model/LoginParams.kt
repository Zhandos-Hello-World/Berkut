package kz.cicada.berkut.feature.auth.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class LoginParams(
    val code: String = "",
    val username: String = "",
    val phoneNumber: String = "",
    val userType: UserType,
) : Parcelable