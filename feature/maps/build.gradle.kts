plugins {
    id(Build.androidLibrary)
    id("com.google.android.libraries.mapsplatform.secrets-gradle-plugin")
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

    AndroidX.all.forEach(::implementation)
    Network.all.forEach(::implementation)
    implementation("com.google.android.gms:play-services-maps:18.2.0")

    implementation(Storage.prefDataStore)
    implementation(Coroutines.coroutines)
    implementation(Navigation.cicreone)

    implementation(Koin.koinCore)
    implementation(Koin.koinAndroid)
    implementation(Google.material)
}