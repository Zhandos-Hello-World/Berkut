plugins {
    id(Build.androidLibrary)
    id(Build.mapsPlatform)
}

apply {
    from("$rootDir/config/java17-android-library.gradle")
    from("$rootDir/config/compose-module.gradle")
    from("$rootDir/config/view-binding.gradle")
}
android {
    namespace = "${ProjectConfig.appId}.feature.maps"

    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation(project(Modules.core))
    implementation(project(Modules.coreData))
    implementation(project(Modules.coreUI))
    implementation(project(Modules.coreUICompose))

    implementation(project(Modules.shareQR))
    implementation(project(Modules.uploadPhoto))

    AndroidX.all.forEach(::implementation)
    Network.all.forEach(::implementation)

    implementation(Google.playServiceMaps)

    implementation(Coroutines.coroutines)
    implementation(Navigation.cicreone)

    implementation(Koin.koinCore)
    implementation(Koin.koinAndroid)
    implementation(Google.material)
}