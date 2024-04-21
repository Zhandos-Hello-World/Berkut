package kz.cicada.berkut.lib.core.ui.compose.external.app.service

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.provider.Settings
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.launch
import androidx.core.app.ActivityCompat.shouldShowRequestPermissionRationale
import androidx.core.content.ContextCompat
import androidx.core.net.toUri
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kz.cicada.berkut.lib.core.R
import kz.cicada.berkut.lib.core.localization.string.VmRes
import kz.cicada.berkut.lib.core.message.Message
import kz.cicada.berkut.lib.core.message.MessageHandler
import kz.cicada.berkut.lib.core.ui.compose.activity.ActivityProvider
import java.io.ByteArrayOutputStream
import java.io.File

private const val KEY_OPEN_CAMERA = "KEY_OPEN_CAMERA"
private const val KEY_OPEN_GALLERY = "KEY_OPEN_GALLERY"
private const val KEY_REQUEST_MULTIPLE_PERMISSIONS = "KEY_REQUEST_MULTIPLE_PERMISSIONS"

class DefaultExternalAppService(
    private val context: Context,
    private val activityProvider: ActivityProvider,
    private val messageHandler: MessageHandler,
) : ExternalAppService {
    private val cameraChannel = Channel<CameraResultEvent>(Channel.UNLIMITED)
    private val galleryChannel = Channel<PhotoPickerResultEvent>(Channel.UNLIMITED)

    override val cameraEvents = cameraChannel.receiveAsFlow()
    override val galleryEvents = galleryChannel.receiveAsFlow()

    private val galleryLauncher = activityProvider.activity?.activityResultRegistry?.register(
            KEY_OPEN_GALLERY,
            ActivityResultContracts.GetContent(),
        ) { uri: Uri? ->
            uri?.let {
                galleryChannel.trySend(
                    PhotoPickerResultEvent.SuccessPhotoResult(
                        bitmap = ImageDecoder.decodeBitmap(
                            ImageDecoder.createSource(context.contentResolver, it),
                        ),
                        uri = it,
                    )
                )
            }
        }

    private val cameraLauncher = activityProvider.activity?.activityResultRegistry?.register(
            KEY_OPEN_CAMERA,
            ActivityResultContracts.TakePicturePreview(),
        ) { bitmap ->
            bitmap?.let {
                cameraChannel.trySend(
                    CameraResultEvent.SuccessCameraResult(
                        bitmap = it,
                        uri = generateUri(it),
                    )
                )
            }
        }

    private val requestMultiplePermissionsLauncher =
        activityProvider.activity?.activityResultRegistry?.register(
                KEY_REQUEST_MULTIPLE_PERMISSIONS,
                ActivityResultContracts.RequestMultiplePermissions(),
            ) { resultsMap ->
                resultsMap.forEach { permission ->
                    when (permission.key) {
                        Manifest.permission.CAMERA -> onHandleCameraPermission(granted = permission.value)
                    }
                }
            }

    override fun openGallery() {
        galleryLauncher?.launch("image/*")
    }

    override fun checkPermissionAndOpenCamera() {
        val permissionCheckResult =
            ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA)
        when {
            permissionCheckResult == PackageManager.PERMISSION_GRANTED -> cameraLauncher?.launch()

            shouldSuggestVisitAppSettingsForCamera() -> messageHandler.showMessage(
                Message(
                    title = VmRes.StrRes(R.string.not_access_camera_title),
                    description = VmRes.StrRes(R.string.not_access_camera_description),
                    positiveButtonText = VmRes.StrRes(R.string.open_settings),
                    positiveButtonAction = {
                        openAppSystemSettings(context)
                    },
                ),
            )

            else -> requestMultiplePermissionsLauncher?.launch(
                arrayOf(Manifest.permission.CAMERA),
            )
        }
    }

    private fun onHandleCameraPermission(granted: Boolean) {
        when {
            granted -> cameraLauncher?.launch()

            shouldSuggestVisitAppSettingsForCamera() -> messageHandler.showMessage(
                Message(
                    title = VmRes.StrRes(R.string.not_access_camera_title),
                    description = VmRes.StrRes(R.string.not_access_camera_description),
                    positiveButtonText = VmRes.StrRes(R.string.open_settings),
                    positiveButtonAction = {
                        openAppSystemSettings(context)
                    },
                ),
            )

            else -> cameraChannel.trySend(CameraResultEvent.PermissionDeniedResult)
        }
    }

    private fun shouldSuggestVisitAppSettingsForCamera(): Boolean =
        activityProvider.activity != null && shouldShowRequestPermissionRationale(
            activityProvider.activity!!,
            Manifest.permission.CAMERA,
        )

    private fun openAppSystemSettings(context: Context) {
        context.startActivity(
            Intent().apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK
                action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
                data = Uri.fromParts("package", context.packageName, null)
            },
        )
    }

    private fun generateUri(
        bitmap: Bitmap,
    ): Uri {
        val file = File(context.cacheDir, "BERKUT_AVATAR")
        file.delete()
        file.createNewFile()
        val fileOutputStream = file.outputStream()
        val byteArrayOutputStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream)
        val bytearray = byteArrayOutputStream.toByteArray()
        fileOutputStream.apply {
            write(bytearray)
            flush()
            close()
        }
        byteArrayOutputStream.close()
        return file.toUri()
    }
}