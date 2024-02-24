plugins {
    id(Build.androidLibrary)
}

apply {
    from("$rootDir/config/java17-android-library.gradle")
    from("$rootDir/config/compose-module.gradle")
}

android {
    namespace = "${ProjectConfig.appId}.lib.core"
}

dependencies {
    implementation(Kotlin.serialization)
    implementation(Kotlin.jsonSerial)

    implementation(Coroutines.coroutines)

    implementation(Util.timber)

    implementation(AndroidX.appCompat)
    implementation(AndroidX.annotation)

    implementation(Storage.prefDataStore)

    implementation(Koin.koinAndroid)
    implementation(Koin.koinCore)
}