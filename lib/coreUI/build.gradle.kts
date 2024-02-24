plugins {
	id(Build.androidLibrary)
}

apply {
	from("$rootDir/config/java17-android-library.gradle")
	from("$rootDir/config/view-binding.gradle")
}

android {
	namespace = "${ProjectConfig.appId}.core.presentation"

	defaultConfig{
		vectorDrawables.useSupportLibrary = true
	}
}

dependencies {
	implementation(project(Modules.core))

	implementation(Google.material)

	implementation(Koin.koinCore)
	implementation(Koin.koinAndroid)

	implementation(Navigation.cicreone)

	implementation(Util.timber)
}