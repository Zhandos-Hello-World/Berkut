plugins {
    id(Build.androidLibrary)
}

apply {
    from("$rootDir/config/java17-android-library.gradle")
}

android {
    namespace = "${ProjectConfig.appId}.lib.core"

    buildFeatures {
        compose = true
    }
}

dependencies {
    implementation(Kotlin.serialization)
    implementation(Kotlin.jsonSerial)

    implementation(Coroutines.coroutines)

    implementation(Util.jodaTime)
    implementation(Util.timber)

    implementation(AndroidX.appCompat)
    implementation(AndroidX.annotation)

    implementation(Storage.prefDataStore)

    implementation(Koin.koinAndroid)
    implementation(Koin.koinCore)

    implementation(Compose.material)
}