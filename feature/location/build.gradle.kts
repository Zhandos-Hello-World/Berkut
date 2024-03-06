plugins {
    id(Build.androidLibrary)
}

apply {
    from("$rootDir/config/java17-android-library.gradle")
    from("$rootDir/config/compose-module.gradle")
}
android {
    namespace = "${ProjectConfig.appId}.feature.location"
}

dependencies {
    implementation(project(Modules.core))
    implementation(project(Modules.coreData))
    implementation(project(Modules.coreUI))
    implementation(project(Modules.coreUICompose))

    implementation(Google.googleGMSLocation)

    Network.all.forEach(::implementation)
    AndroidX.all.forEach(::implementation)

    implementation(Navigation.cicreone)
    implementation(Koin.koinAndroid)
    implementation(Koin.koinCore)

    implementation("com.github.NaikSoftware:StompProtocolAndroid:1.6.6")
    implementation(Google.material)
}