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
    implementation(project(Modules.coreUI))
    implementation(project(Modules.coreUICompose))

    AndroidX.all.forEach(::implementation)

    implementation(Navigation.cicreone)

    implementation(Google.material)
}