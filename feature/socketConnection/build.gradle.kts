plugins {
    id(Build.androidLibrary)
}

apply {
    from("$rootDir/config/java17-android-library.gradle")
    from("$rootDir/config/compose-module.gradle")
}
android {
    namespace = "${ProjectConfig.appId}.feature.socketConnection"
}

dependencies {
    implementation(project(Modules.core))
    implementation(project(Modules.coreData))

    AndroidX.all.forEach(::implementation)
    Network.all.forEach(::implementation)

    implementation(Coroutines.coroutines)
    implementation(SocketConnection.rx)
    implementation(SocketConnection.stomp)
}