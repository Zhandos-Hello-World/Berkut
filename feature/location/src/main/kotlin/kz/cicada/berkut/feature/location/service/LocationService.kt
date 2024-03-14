package kz.cicada.berkut.feature.location.service

import android.app.Service
import android.content.Intent
import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kz.cicada.berkut.feature.location.data.repository.LocationRepository
import kz.cicada.berkut.feature.location.ext.isConnectedToInternet
import org.koin.android.ext.android.inject

class LocationService : Service() {
    private val serviceScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)
    private val locationRepository: LocationRepository by inject()
    private val locationClient: LocationClient by inject()

    override fun onBind(p0: Intent?) = null

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        when (intent?.action) {
            ACTION_START -> start()
            ACTION_STOP -> stop()
        }
        return super.onStartCommand(intent, flags, startId)
    }

    private fun start() {
        locationClient
            .getLocationUpdates(LOCATION_UPDATE_INTERVAL)
            .catch { e -> e.printStackTrace() }
            .onEach { location ->
                if (applicationContext.isConnectedToInternet()) {
                    serviceScope.launch(Dispatchers.IO) {
                        locationRepository.pushCurrentLocation(
                            location.latitude,
                            location.longitude
                        )
                        Log.d("Location", "location(${location.latitude}, ${location.longitude})")
                    }
                }
            }
            .launchIn(serviceScope)
    }

    private fun stop() {
        stopSelf()
    }

    override fun onDestroy() {
        super.onDestroy()
        serviceScope.cancel()
    }

    companion object {
        const val ACTION_START = "ACTION_START"
        const val ACTION_STOP = "ACTION_STOP"
        private const val LOCATION_UPDATE_INTERVAL = 3_000L
    }
}
