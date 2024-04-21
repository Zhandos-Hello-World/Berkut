package kz.cicada.berkut.feature.maps.presentation.socket

import android.os.Parcelable
import kotlinx.coroutines.flow.first
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize
import kz.cicada.berkut.feature.socketconnection.presentation.SocketBehavior
import kz.cicada.berkut.lib.core.data.local.UserPreferences
import kz.cicada.berkut.lib.core.data.network.UserType
import org.koin.core.component.inject

@Parcelize
class MapsSocketBehavior: SocketBehavior, Parcelable {
    @IgnoredOnParcel
    private val userPref: UserPreferences by inject()

    override suspend fun accessableForUserType(): Boolean = userPref.getType().first() == UserType.PARENT.name

    override suspend fun onPath(): String {
        val id = userPref.getId().first()
        return "/user/${id}/geo-data"
    }
}