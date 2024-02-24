plugins {
    id(Build.androidLibrary)
}

apply {
    from("$rootDir/config/java17-android-library.gradle")
    from("$rootDir/config/compose-module.gradle")
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


    implementation(Util.timber)
    Util.Hyperion.all.forEach(::debugImplementation)
    debugImplementation(Util.chuckerLibrary)
    releaseImplementation(Util.chuckerLibraryNoOp)
}