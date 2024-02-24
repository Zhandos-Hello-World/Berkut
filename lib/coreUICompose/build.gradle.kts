plugins {
    id(Build.androidLibrary)
}

apply {
    from("$rootDir/config/java17-android-library.gradle")
    from("$rootDir/config/compose-module.gradle")
}
android {
    namespace = "${ProjectConfig.appId}.lib.core.ui.compose"
}

dependencies {
    implementation(project(Modules.core))
    implementation(project(Modules.coreUI))

    AndroidX.all.forEach(::implementation)

    implementation(Coroutines.coroutines)

    implementation(Koin.koinCore)
    implementation(Koin.koinAndroid)
    implementation(Google.material)
}