object Versions {
    const val core_ktx = "1.7.0"
    const val appcompat = "1.4.1"
    const val material = "1.5.0"
    const val constraintLayout = "2.1.3"

    const val retrofit = "2.9.0"
    const val loggingInterceptor = "5.0.0-alpha.4"

    const val coroutines="1.3.9"

    const val lifecycle="2.2.0"

    const val navigation= "2.4.2"
}

object BuildPlugins{
    const val application="7.2.0"
    const val library="7.2.0"
    const val kotlin="1.6.21"
    const val hilt = "2.40"
}

object Android {
    const val minSdk = 21
    const val targetSdk = 32
    const val compileSdk = 32
    const val applicationId = "com.aelfattah.ahmed.currency"
    const val versionCode = 1
    const val versionName = "0.1"
    const val testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
}

object Libs {
    const val coreKtx = "androidx.core:core-ktx:${Versions.core_ktx}"
    const val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
    const val material = "com.google.android.material:material:${Versions.material}"
    const val constraintLayout =
        "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"

    //Retrofit
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val gson = "com.google.code.gson:gson:${Versions.retrofit}"
    const val converterGson =
        "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
    const val loggingInterceptor =
        "com.squareup.okhttp3:logging-interceptor:${Versions.loggingInterceptor}"

    const val hiltAndroid="com.google.dagger:hilt-android:${BuildPlugins.hilt}"
    const val hiltAndroidCompiler="com.google.dagger:hilt-android-compiler:${BuildPlugins.hilt}"

    const val coroutines="org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"

    const val livedata="androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycle}"
    const val viewModel="androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"

    const val navigationUI="androidx.navigation:navigation-ui-ktx:${Versions.navigation}"
    const val navigationFragment="androidx.navigation:navigation-fragment-ktx:${Versions.navigation}"
}

object TestLibs {
    const val junit = "junit:junit:4.13.2"
    const val extJunit = "androidx.test.ext:junit:1.1.3"
    const val espresso = "androidx.test.espresso:espresso-core:3.4.0"
}