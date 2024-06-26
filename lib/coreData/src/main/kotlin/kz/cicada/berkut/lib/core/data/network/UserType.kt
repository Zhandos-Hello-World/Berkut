package kz.cicada.berkut.lib.core.data.network

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
sealed class UserType(val name: String) : Parcelable {
    data object PARENT: UserType("PARENT")
    data object CHILD: UserType("CHILD")
}