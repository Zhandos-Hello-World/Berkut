package kz.cicada.berkut.feature.socketconnection.data

internal object Endpoints {
    const val SOCKET_URL = "ws://berkut-mobile-app-dev.up.railway.app/ws-connection"

    fun geoData(userId: Int) = "/user/${userId}/geo-data"
}