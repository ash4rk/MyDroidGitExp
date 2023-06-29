plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("org.jetbrains.kotlin.kapt")
    id("org.jetbrains.kotlin.plugin.parcelize")
    id("dagger.hilt.android.plugin")
    id("com.google.devtools.ksp") version Versions.KSP
}

android {

    namespace = "com.example.mydroidgitexp.core"
    compileSdk = Configuration.COMPILE_SDK

    defaultConfig {
        minSdk = Configuration.MIN_SDK
        targetSdk = Configuration.TARGET_SDK
        ksp {
            arg("room.schemaLocation", "$projectDir/schemas")
        }
    }

    sourceSets.getByName("test") {
        assets.srcDir(files("$projectDir/schemas"))
    }
}

dependencies {
    // coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.COROUTINES}")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.COROUTINES}")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.COROUTINES}")

    // di
    implementation("com.google.dagger:hilt-android:${Versions.HILT}")
    kapt("com.google.dagger:hilt-android-compiler:${Versions.HILT}")

    // database
    implementation("androidx.room:room-runtime:${Versions.ANDROIDX_ROOM}")
    implementation("androidx.room:room-ktx:${Versions.ANDROIDX_ROOM}")
    ksp("androidx.room:room-compiler:${Versions.ANDROIDX_ROOM}")
    testImplementation("androidx.arch.core:core-testing:${Versions.ANDROIDX_ARCH_CORE}")

    // network
    implementation("com.github.skydoves:sandwich:${Versions.SANDWICH}")
    implementation("com.squareup.retrofit2:converter-gson:${Versions.RETROFIT}")
    implementation("com.squareup.retrofit2:converter-moshi:${Versions.RETROFIT}")
    implementation("com.squareup.okhttp3:logging-interceptor:${Versions.OKHTTP}")
    testImplementation("com.squareup.okhttp3:mockwebserver:${Versions.OKHTTP}")
    testImplementation("androidx.arch.core:core-testing:${Versions.ANDROIDX_ARCH_CORE}")

    // json parsing
    implementation("com.squareup.moshi:moshi-kotlin:${Versions.MOSHI}")
    ksp("com.squareup.moshi:moshi-kotlin-codegen:${Versions.MOSHI}")

    // logger
    api("com.jakewharton.timber:timber:${Versions.TIMBER}")

}