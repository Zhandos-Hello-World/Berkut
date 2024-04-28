plugins {
    id(Build.androidLibrary)
}

apply {
    from("$rootDir/config/java17-android-library.gradle")
    from("$rootDir/config/compose-module.gradle")
}
android {
    namespace = "${ProjectConfig.appId}.feature.children"
}

dependencies {
    implementation(project(Modules.core))
    implementation(project(Modules.coreData))
    implementation(project(Modules.coreUI))
    implementation(project(Modules.coreUICompose))

    implementation(project(Modules.chooser))
    implementation(project(Modules.shareQR))

    AndroidX.all.forEach(::implementation)
    Network.all.forEach(::implementation)

    implementation(Coroutines.coroutines)
    implementation(Navigation.cicreone)
    implementation(Google.playServiceMaps)


    implementation(Koin.koinCore)
    implementation(Koin.koinAndroid)
    implementation(Google.material)
    implementation(Coil.coilCompose)
}