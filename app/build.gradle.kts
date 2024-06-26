plugins {
    id(Build.androidApplication)
    id(Build.kotlinAndroid)
    id(Build.kotlinKspPlugin)
    id("com.google.android.libraries.mapsplatform.secrets-gradle-plugin")
    id("com.google.gms.google-services")
}

apply {
    from("$rootDir/config/java17-android-library.gradle")
    from("$rootDir/config/compose-module.gradle")
    from("$rootDir/config/view-binding.gradle")
}

android {
    namespace = ProjectConfig.appId
    compileSdk = ProjectConfig.compileSdk

    defaultConfig {
        applicationId = ProjectConfig.appId
        minSdk = ProjectConfig.minSdk
        targetSdk = ProjectConfig.targetSdk
        versionCode = ProjectConfig.versionCode
        versionName = ProjectConfig.versionName

        resourceConfigurations.addAll(listOf("en", "ru", "kk"))
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        debug {
            isDefault = true
            isDebuggable = true
            isMinifyEnabled = false
            isShrinkResources = false

            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )

        }
        release {
            isDebuggable = false
            isMinifyEnabled = false
            isShrinkResources = false
            signingConfig = signingConfigs.getByName("debug")

            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
            )
        }

        secrets {
            // Optionally specify a different file name containing your secrets.
            // The plugin defaults to "local.properties"
            propertiesFileName = "secrets.properties"

            // A properties file containing default secret values. This file can be
            // checked in version control.
            defaultPropertiesFileName = "local.defaults.properties"

            // Configure which keys should be ignored by the plugin by providing regular expressions.
            // "sdk.dir" is ignored by default.
            ignoreList.add("keyToIgnore") // Ignore the key "keyToIgnore"
            ignoreList.add("sdk.*")       // Ignore all keys matching the regexp "sdk.*"
        }
    }

    packaging {
        resources {
            excludes += "META-INF/*"
        }
    }
}

dependencies {
    // Core modules
    implementation(project(Modules.core))
    implementation(project(Modules.coreData))
    implementation(project(Modules.coreUI))
    implementation(project(Modules.coreUICompose))

    //Feature modules
    implementation(project(Modules.auth))
    implementation(project(Modules.result))
    implementation(project(Modules.chooser))
    implementation(project(Modules.language))
    implementation(project(Modules.maps))
    implementation(project(Modules.shareQR))
    implementation(project(Modules.uploadPhoto))
    implementation(project(Modules.location))
    implementation(project(Modules.socketConnection))
    implementation(project(Modules.profile))
    implementation(project(Modules.sos))
    implementation(project(Modules.savedLocations))
    implementation(project(Modules.tasks))
    implementation(project(Modules.children))

    implementation(platform("com.google.firebase:firebase-bom:32.8.0"))
    implementation("com.google.firebase:firebase-messaging")
    implementation("com.google.firebase:firebase-analytics")

    // Dependencies
    implementation(AndroidX.coreKtx)
    implementation(AndroidX.appCompat)

    implementation(Google.material)

    implementation(Navigation.cicreone)

    implementation(Koin.koinCore)
    implementation(Koin.koinAndroid)

    Network.all.forEach(::implementation)
}