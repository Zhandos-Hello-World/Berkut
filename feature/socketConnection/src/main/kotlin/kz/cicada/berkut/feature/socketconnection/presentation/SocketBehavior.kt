package kz.cicada.berkut.feature.socketconnection.presentation

import android.os.Parcelable
import org.koin.core.component.KoinComponent

interface SocketBehavior : Parcelable, KoinComponent {

    suspend fun onPath(): String
}