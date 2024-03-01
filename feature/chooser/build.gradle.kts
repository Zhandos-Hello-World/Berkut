plugins {
    id(Build.androidLibrary)
}

apply {
    from("$rootDir/config/java17-android-library.gradle")
    from("$rootDir/config/compose-module.gradle")
}
android {
    namespace = "${ProjectConfig.appId}.feature.chooser"
}

dependencies {
    implementation(project(Modules.core))
    implementation(project(Modules.coreUI))
    implementation(project(Modules.coreUICompose))

    AndroidX.all.forEach(::implementation)

    implementation(Coroutines.coroutines)
    implementation(Navigation.cicreone)

    implementation(Koin.koinCore)
    implementation(Koin.koinAndroid)
    implementation(Google.material)
}