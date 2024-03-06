package kz.cicada.berkut.feature.location.external

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.provider.Settings
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import kz.cicada.berkut.feature.location.ext.isServiceRunning
import kz.cicada.berkut.feature.location.service.LocationService
import kz.cicada.berkut.lib.core.R
import kz.cicada.berkut.lib.core.localization.string.VmRes
import kz.cicada.berkut.lib.core.message.Message
import kz.cicada.berkut.lib.core.message.MessageHandler
import kz.cicada.berkut.lib.core.ui.compose.activity.ActivityProvider


private const val KEY_REQUEST_MULTIPLE_PERMISSIONS = "KEY_REQUEST_MULTIPLE_PERMISSIONS"

internal class DefaultExternalLocationService(
    private val context: Context,
    private val activityProvider: ActivityProvider,
    private val messageHandler: MessageHandler,
) : ExternalLocationService {

    private val requestMultiplePermissionsLauncher by lazy {
        activityProvider
            .activity
            ?.activityResultRegistry
            ?.register(
                KEY_REQUEST_MULTIPLE_PERMISSIONS,
                ActivityResultContracts.RequestMultiplePermissions(),
            ) { resultsMap ->
                resultsMap.forEach { permission ->
                    when (permission.key) {
                        Manifest.permission.ACCESS_BACKGROUND_LOCATION -> onHandleCameraPermission(granted = permission.value)
                        Manifest.permission.CAMERA -> onHandleCameraPermission(granted = permission.value)
                    }
                }
            }
    }

    override fun startLocationService() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            requestMultiplePermissionsLauncher!!.launch(
                arrayOf(
                    Manifest.permission.ACCESS_BACKGROUND_LOCATION),
            )
            val isPermissionGPSBackground = ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_BACKGROUND_LOCATION,
            ) == PackageManager.PERMISSION_GRANTED


            startLocationWithPermission(isPermissionGPSBackground)
        } else {
            startLocationWithPermission(true)
        }
    }

    override fun closeLocationService() {
        Intent(activityProvider.activity?.applicationContext, LocationService::class.java).apply {
            action = LocationService.ACTION_STOP
            activityProvider.activity?.startService(this)
        }
    }

    private fun startLocationWithPermission(permission: Boolean) {
        if (permission) {
            if (activityProvider.activity?.isServiceRunning(LocationService::class.java) == false) {
                Intent(
                    activityProvider.activity?.applicationContext,
                    LocationService::class.java
                ).apply {
                    action = LocationService.ACTION_START
                    activityProvider.activity?.startService(this)
                }
            }
        } else {
            messageHandler.showMessage(
                Message(
                    title = VmRes.StrRes(R.string.not_access_camera_title),
                    description = VmRes.StrRes(R.string.not_access_camera_description),
                    positiveButtonText = VmRes.StrRes(R.string.open_settings),
                    positiveButtonAction = {
                        openAppSystemSettings(context)
                    },
                )
            )
        }
    }

    private fun openAppSystemSettings(context: Context) {
        context.startActivity(
            Intent().apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK
                action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
                data = Uri.fromParts("package", context.packageName, null)
            },
        )
    }

    private fun onHandleCameraPermission(granted: Boolean) {
        when {
            granted -> startLocationService()
            else -> startLocationService()
        }
    }

}