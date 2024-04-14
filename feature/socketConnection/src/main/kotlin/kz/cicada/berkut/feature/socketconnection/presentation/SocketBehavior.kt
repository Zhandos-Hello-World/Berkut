package kz.cicada.berkut.feature.socketconnection.presentation

import android.os.Parcelable
import org.koin.core.component.KoinComponent

interface SocketBehavior : Parcelable, KoinComponent {

    fun getBaseUrl(): String = DEFAULT_SOCKET_CONNECTION_BASE_URL

    suspend fun accessableForUserType(): Boolean

    suspend fun onPath(): String

    companion object {
        private const val DEFAULT_SOCKET_CONNECTION_BASE_URL =
            "ws://berkut-mobile-app-production.up.railway.app/ws-connection"
    }
}