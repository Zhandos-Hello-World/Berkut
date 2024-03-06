package kz.cicada.berkut.feature.location.ext

import android.Manifest
import android.app.ActivityManager
import android.content.Context
import android.content.pm.PackageManager
import android.location.LocationManager
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.provider.Settings
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat


fun Context.isConnectedToInternet(): Boolean {
    val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val network = connectivityManager.activeNetwork ?: return false
    val capabilities = connectivityManager.getNetworkCapabilities(network) ?: return false
    return capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
}

fun Context.hasLocationPermission(): Boolean {
    return ContextCompat.checkSelfPermission(
        this,
        Manifest.permission.ACCESS_COARSE_LOCATION
    ) == PackageManager.PERMISSION_GRANTED &&
            ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
}

@RequiresApi(Build.VERSION_CODES.Q)
fun Context.hasBackgroundLocationPermission(): Boolean {
    return ContextCompat.checkSelfPermission(
        this,
        Manifest.permission.ACCESS_BACKGROUND_LOCATION
    ) == PackageManager.PERMISSION_GRANTED
}


fun Context.isServiceRunning(serviceClass: Class<*>): Boolean {
    val activityManager = this.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
    val services = activityManager.getRunningServices(Integer.MAX_VALUE)
    for (service in services) {
        if (serviceClass.name == service.service.className) {
            return true
        }
    }
    return false
}

fun Context.getLocationPermissionType() : LocationPermissionType{
    val locationManager = this.getSystemService(Context.LOCATION_SERVICE) as LocationManager

    val locationPermissionStatus = ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
    return if (locationPermissionStatus) {
        val isLocationEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(
            LocationManager.NETWORK_PROVIDER)
        val locationMode = Settings.Secure.getInt(this.contentResolver, Settings.Secure.LOCATION_MODE)

        if (isLocationEnabled && locationMode != Settings.Secure.LOCATION_MODE_OFF) {
            // the other app has been granted the location permission "Allow all the time"
            LocationPermissionType.ALWAYS
        } else {
            // the other app has been granted the location permission "Allow only while using the app"
            LocationPermissionType.WHILE_USING
        }
    } else {
        // the other app has not been granted the location permission
        LocationPermissionType.OFF
    }
}

enum class LocationPermissionType{
    OFF,
    WHILE_USING,
    ONE_TIME,
    ALWAYS
}

