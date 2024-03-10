package kz.cicada.berkut.feature.socketconnection.presentation

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SocketLauncher(
    val path: String,
) : Parcelable