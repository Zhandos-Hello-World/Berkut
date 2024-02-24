// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id(Build.androidApplication) version Build.gradlePluginVersion apply false
    id(Build.androidLibrary) version Build.gradlePluginVersion apply false
    id(Build.kotlinAndroid) version Build.kotlinVersion apply false
    id(Build.kotlinJvm) version Build.kotlinVersion apply false
    id(Build.kotlinKspPlugin) version Build.kspVersion apply false
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}