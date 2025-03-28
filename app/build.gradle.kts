plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.kotlinAndroid)
    alias(libs.plugins.kotlinCompose)
    alias(libs.plugins.hiltAndroid)
    alias(libs.plugins.ksp)
    alias(libs.plugins.kotlinSerialization)
}

android {
    namespace = "com.adrian.movieappcompose"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.adrian.movieappcompose"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {

    implementation(libs.androidxCoreKtx)
    implementation(libs.androidxLifecycleRuntimeKtx)
    implementation(libs.androidxActivityCompose)
    implementation(platform(libs.androidxComposeBom))
    implementation(libs.androidxUi)
    implementation(libs.androidxUiGraphics)
    implementation(libs.androidxUiToolingPreview)
    implementation(libs.androidxMaterial3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidxJunit)
    androidTestImplementation(libs.androidxEspressoCore)
    androidTestImplementation(platform(libs.androidxComposeBom))
    androidTestImplementation(libs.androidxUiTestJunit4)
    debugImplementation(libs.androidxUiTooling)
    debugImplementation(libs.androidxUiTestManifest)

    implementation(libs.datastore)
    implementation(libs.constraintCompose)
    implementation(libs.navigationCompose)
    implementation(libs.materialIconsExtendedCompose)
    implementation(libs.bundles.room)
    ksp(libs.roomCompiler)
    implementation(libs.bundles.retrofit)
    implementation(libs.bundles.hilt)
    implementation(libs.bundles.lifecycle)
    ksp(libs.hiltCompiler)
    implementation(libs.kotlin)
    implementation(libs.coroutines)
    implementation(libs.coil)
    implementation(libs.accompanistFlowLayout)

    debugImplementation(libs.leakCanary)

//    debugImplementation(libs.chuckDebug)
//    releaseImplementation(libs.chuckRelease)
}
