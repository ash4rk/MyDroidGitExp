plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("org.jetbrains.kotlin.kapt")
    id("org.jetbrains.kotlin.plugin.parcelize")
    id("dagger.hilt.android.plugin")
}

android {
    namespace = "com.example.mydroidgitexp"

    compileSdk = Configuration.COMPILE_SDK
    defaultConfig {
        applicationId = "com.example.mydroidgitexp"
        minSdk = Configuration.MIN_SDK
        targetSdk = Configuration.TARGET_SDK
        versionCode = Configuration.VERSION_CODE
        versionName = Configuration.VERSION_NAME
        vectorDrawables.useSupportLibrary = true
        testInstrumentationRunner = "com.example.mydroidgitexp.AppTestRunner"
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    buildFeatures {
        dataBinding = true
    }

    hilt {
        enableAggregatingTask = true
    }

    kotlin {
        sourceSets.configureEach {
            kotlin.srcDir("$buildDir/generated/ksp/$name/kotlin/")
        }
    }

    testOptions {
        unitTests {
            isIncludeAndroidResources = true
            isReturnDefaultValues = true
        }
    }

    buildTypes {
        create("benchmark") {
            isDebuggable = true
            signingConfig = getByName("debug").signingConfig
            matchingFallbacks += listOf("release")
        }
    }

    lint {
        abortOnError = false
    }
}

dependencies {
    // modules
    implementation(project(mapOf("path" to ":core")))

    // androidx
    implementation("com.google.android.material:material:${Versions.MATERIAL}")
    implementation("androidx.fragment:fragment-ktx:${Versions.ANDROIDX_FRAGMENT}")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.ANDROIDX_LIFECYCLE}")
    implementation("androidx.startup:startup-runtime:${Versions.ANDROIDX_STARTUP}")
    implementation("androidx.navigation:navigation-fragment-ktx:${Versions.ANDROIDX_NAVIGATION_FRAGMENT}")
    implementation("androidx.navigation:navigation-ui-ktx:${Versions.ANDROIDX_NAVIGATION_UI}")

    // data binding
    implementation("com.github.skydoves:bindables:${Versions.BINDABLES}")

    // di
    implementation("com.google.dagger:hilt-android:${Versions.HILT}")
    kapt("com.google.dagger:hilt-android-compiler:${Versions.HILT}")
    androidTestImplementation("com.google.dagger:hilt-android-testing:${Versions.HILT}")
    kaptAndroidTest("com.google.dagger:hilt-compiler:${Versions.HILT}")

    kapt("androidx.hilt:hilt-compiler:1.0.0")

    // coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.COROUTINES}")

    // recyclerView
    implementation("androidx.recyclerview:recyclerview:${Versions.RECYCLER_VIEW}")
    implementation("com.github.skydoves:baserecyclerviewadapter:${Versions.BASE_ADAPTER}")

    // whatIf
    implementation("com.github.skydoves:whatif:${Versions.WHAT_IF}")

    // glide
    implementation("com.github.bumptech.glide:glide:${Versions.GLIDE}")
    implementation("com.github.florent37:glidepalette:${Versions.GLIDE_PALETTE}")

    // custom views
    implementation("com.github.skydoves:androidribbon:${Versions.ANDROID_RIBBON}")
    implementation("com.github.skydoves:progressview:${Versions.PROGRESS_VIEW}")
}