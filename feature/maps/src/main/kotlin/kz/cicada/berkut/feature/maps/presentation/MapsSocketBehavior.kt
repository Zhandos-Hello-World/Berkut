package kz.cicada.berkut.feature.maps.presentation

import android.os.Parcelable
import kotlinx.coroutines.flow.first
import kotlinx.parcelize.Parcelize
import kz.cicada.berkut.feature.socketconnection.presentation.SocketBehavior
import kz.cicada.berkut.lib.core.data.local.UserPreferences
import org.koin.core.component.inject

@Parcelize
class MapsSocketBehavior: SocketBehavior, Parcelable {
    private val userPref: UserPreferences by inject()

    override suspend fun onPath(): String {
        val id = userPref.getId().first()
        return "/user/${id}/geo-data"
    }
}