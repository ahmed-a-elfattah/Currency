import java.io.FileInputStream
import java.util.*

plugins {
    id("com.android.application")
    kotlin("android")
    id("dagger.hilt.android.plugin")
    kotlin("kapt")
}

android {
    compileSdk = Android.compileSdk

    defaultConfig {
        applicationId = Android.applicationId
        minSdk = Android.minSdk
        targetSdk = Android.targetSdk
        versionCode = Android.versionCode
        versionName = Android.versionName
        testInstrumentationRunner = Android.testInstrumentationRunner
    }

    val localProperties = Properties().apply {
        load(FileInputStream(File(rootProject.rootDir, "local.properties")))
    }

    buildTypes {
        getByName("debug") {
            buildConfigField(
                "String",
                "fixer_api_key",
                localProperties.getProperty("FIXER_API_KEY")
            )
        }
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation(Libs.coreKtx)
    implementation(Libs.appcompat)
    implementation(Libs.constraintLayout)
    implementation(Libs.material)

    implementation(Libs.retrofit)
    implementation(Libs.gson)
    implementation(Libs.converterGson)
    implementation(Libs.loggingInterceptor)

    implementation(Libs.hiltAndroid)
    kapt(Libs.hiltAndroidCompiler)

    implementation(Libs.coroutines)

    testImplementation(TestLibs.junit)

    androidTestImplementation(TestLibs.extJunit)
    androidTestImplementation(TestLibs.espresso)
}

// Allow references to generated code
kapt {
    correctErrorTypes = true
}