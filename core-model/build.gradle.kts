plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("org.jetbrains.kotlin.plugin.parcelize")
    id("com.google.devtools.ksp") version "1.8.10-1.0.9"
}

android {
    namespace = "com.example.mydroidgitexp.core_model"
    compileSdk = 33

    defaultConfig {
        minSdk = 24
        targetSdk = 33
    }
}

dependencies {
    implementation("com.squareup.moshi:moshi-kotlin:1.14.0")
    ksp("com.squareup.moshi:moshi-kotlin-codegen:1.14.0")
    api("com.jakewharton.timber:timber:5.0.1")
}