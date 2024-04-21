plugins {
    id(Build.androidLibrary)
}

apply {
    from("$rootDir/config/java17-android-library.gradle")
    from("$rootDir/config/compose-module.gradle")
    from("$rootDir/config/room-database-module.gradle")
}
android {
    namespace = "${ProjectConfig.appId}.lib.core.data"
}

dependencies {
    implementation(project(Modules.core))

    AndroidX.all.forEach(::implementation)
    Network.all.forEach(::implementation)

    implementation(Kotlin.serialization)
    implementation(Kotlin.jsonSerial)

    implementation(Coroutines.coroutines)

    implementation(Koin.koinCore)
    implementation(Koin.koinAndroid)

    implementation(Storage.prefDataStore)
    implementation(Util.timber)

    //CHUCKER FOR LOG REQUESTS
    debugImplementation(Util.chuckerLibrary)
    releaseImplementation(Util.chuckerLibraryNoOp)

    //HYPERION FOR DEBUG
    Util.Hyperion.all.forEach(::debugImplementation)
    Util.Hyperion.all.forEach(::releaseImplementation)
}