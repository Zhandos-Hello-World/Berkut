package kz.cicada.berkut.feature.savedlocations.presentation.maps

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kz.cicada.berkut.feature.savedlocations.navigation.SavedLocationsScreens
import kz.cicada.berkut.feature.savedlocations.presentation.confirm.SavedLocationsConfirmLauncher
import kz.cicada.berkut.feature.savedlocations.presentation.confirm.SavedLocationsConfirmResultEvent
import kz.cicada.berkut.lib.core.ui.base.BaseViewModel
import kz.cicada.berkut.lib.core.ui.event.CloseScreenEvent
import kz.cicada.berkut.lib.core.ui.event.OpenScreenEvent
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class SavedLocationsMapViewModel : BaseViewModel() {

    fun navigateToConfirm(
        latitude: Double,
        longitude: Double,
        radius: Double,
    ) {
        viewModelScope.launch {
            val location = reverseGeocoding(
                latitude = latitude,
                longitude = longitude,
                apiKey = "AIzaSyBk7l539DZ66QmlhppWV6PZhLXDr_aKUD4",
            )
            withContext(Dispatchers.Main) {
                sendEvent(
                    OpenScreenEvent(
                        screen = SavedLocationsScreens.SavedLocationConfirm(
                            launcher = SavedLocationsConfirmLauncher(
                                latitude = latitude,
                                longitude = longitude,
                                radius = radius,
                                address = location,
                            ),
                        ),
                    )
                )
            }
        }
    }

    override fun onNavigationResult(result: Any) {
        super.onNavigationResult(result)
        if (result == SavedLocationsConfirmResultEvent) {
            navigateUp()
        }
    }

    fun navigateUp() {
        sendEvent(CloseScreenEvent)
    }

    suspend fun reverseGeocoding(latitude: Double, longitude: Double, apiKey: String): String = withContext(Dispatchers.IO) {
        val url = URL("https://maps.googleapis.com/maps/api/geocode/json?latlng=$latitude,$longitude&key=$apiKey")
        val connection = url.openConnection() as HttpURLConnection
        connection.requestMethod = "GET"
        connection.connect()

        val reader = BufferedReader(InputStreamReader(connection.inputStream))
        val result = reader.readText()
        reader.close()

        result
    }
}