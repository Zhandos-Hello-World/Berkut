plugins {
    id(Build.androidLibrary)
}

apply {
    from("$rootDir/config/java17-android-library.gradle")
    from("$rootDir/config/compose-module.gradle")
}
android {
    namespace = "${ProjectConfig.appId}.feature.shareqr"
}

dependencies {
    implementation(project(Modules.core))
    implementation(project(Modules.coreData))
    implementation(project(Modules.coreUI))
    implementation(project(Modules.coreUICompose))

    AndroidX.all.forEach(::implementation)
    Network.all.forEach(::implementation)

    implementation(Coroutines.coroutines)
    implementation(Navigation.cicreone)

    implementation(Koin.koinCore)
    implementation(Koin.koinAndroid)
    implementation(Google.material)

    implementation(AndroidX.Camera.camera2)
    implementation(AndroidX.Camera.cameraLifecycle)
    implementation(AndroidX.Camera.cameraView)
    implementation(Google.zxing)
}