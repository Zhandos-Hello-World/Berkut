package kz.cicada.berkut.feature.shareqr.presentation.scan

import android.Manifest
import android.content.pm.PackageManager
import android.util.Size
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import kz.cicada.berkut.feature.shareqr.presentation.scan.compose.TransparentClipLayout
import kz.cicada.berkut.feature.shareqr.presentation.scan.ext.QrCodeAnalyzer
import kz.cicada.berkut.lib.core.ui.compose.theme.AppTheme

@Composable
fun ScanQRContent(
    controller: ScanQRViewController,
) {
    var code by remember {
        mutableStateOf("")
    }
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    val cameraProviderFuture = remember {
        ProcessCameraProvider.getInstance(context)
    }
    var hasCamPermission by remember {
        mutableStateOf(
            ContextCompat.checkSelfPermission(
                context, Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_GRANTED
        )
    }
    val launcher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.RequestPermission(),
            onResult = { granted ->
                hasCamPermission = granted
            })
    LaunchedEffect(key1 = true) {
        launcher.launch(Manifest.permission.CAMERA)
    }
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        if (hasCamPermission) {
            AndroidView(
                factory = { context ->
                    val previewView = PreviewView(context)
                    val preview = Preview.Builder().build()
                    val selector =
                        CameraSelector.Builder().requireLensFacing(CameraSelector.LENS_FACING_BACK)
                            .build()
                    preview.setSurfaceProvider(previewView.surfaceProvider)
                    val imageAnalysis = ImageAnalysis.Builder().setTargetResolution(
                        Size(
                            previewView.width, previewView.height
                        )
                    ).setBackpressureStrategy(STRATEGY_KEEP_ONLY_LATEST).build()
                    imageAnalysis.setAnalyzer(ContextCompat.getMainExecutor(context),
                        QrCodeAnalyzer { result ->
                            code = result
                            controller.detekt(result)
                        })
                    try {
                        cameraProviderFuture.get().bindToLifecycle(
                            lifecycleOwner, selector, preview, imageAnalysis
                        )
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                    previewView
                },
                modifier = Modifier.fillMaxSize(),
            )

            TransparentClipLayout(
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}

@androidx.compose.ui.tooling.preview.Preview
@Composable
private fun ScanQRContentPreview() {
    AppTheme {
        ScanQRContent(
            controller = object : ScanQRViewController {
                override fun detekt(url: String) = Unit
            },
        )
    }
}