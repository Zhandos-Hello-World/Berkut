package kz.cicada.berkut.feature.location.data.repository

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.first
import kz.cicada.berkut.feature.location.data.location.LocationApi
import kz.cicada.berkut.feature.location.data.model.PushLocationRequest
import kz.cicada.berkut.lib.core.data.local.UserPreferences
import kz.cicada.berkut.lib.core.ui.compose.system.DeviceAppService

class LocationRepository(
    private val api: LocationApi,
    private val defaultUserPreferences: UserPreferences,
    private val dispatcher: CoroutineDispatcher,
    private val deviceAppService: DeviceAppService,
) {

    suspend fun pushCurrentLocation(
        latitude: Double,
        longitude: Double,
    ) {
        with(dispatcher) {
            api.pushLocation(
                request = PushLocationRequest(
                    userId = defaultUserPreferences.getId().first().toInt(),
                    latitude = latitude.toString(),
                    longitude = longitude.toString(),
                    timestamp = System.currentTimeMillis().toString(),
                    timeZone = "5",
                    username = defaultUserPreferences.getUserName().first(),
                    battery = deviceAppService.getBatteryPercentage(),
                ),
            )
        }
    }
}
