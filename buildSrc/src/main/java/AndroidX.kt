object AndroidX {
    private const val coreKtxVersion = "1.12.0"
    const val coreKtx = "androidx.core:core-ktx:$coreKtxVersion"

    private const val appCompatVersion = "1.6.1"
    const val appCompat = "androidx.appcompat:appcompat:$appCompatVersion"

    private const val documentFileVersion = "1.0.1"
    const val documentFile = "androidx.documentfile:documentfile:$documentFileVersion"

    private const val annotationVersion = "1.7.1"
    const val annotation = "androidx.annotation:annotation-jvm:$annotationVersion"

    object Camera {
        private const val cameraVersion = "1.3.2"

        const val camera2 = "androidx.camera:camera-camera2:$cameraVersion"
        const val cameraLifecycle = "androidx.camera:camera-lifecycle:$cameraVersion"
        const val cameraView = "androidx.camera:camera-view:$cameraVersion"
    }

    val all = listOf(
        coreKtx,
        appCompat,
        documentFile,
        annotation,
    )
}