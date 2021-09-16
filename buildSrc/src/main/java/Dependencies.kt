import org.gradle.api.JavaVersion

object Config {
    const val application_id = "com.example.gb_translator"
    const val compile_sdk = 30
    const val min_sdk = 21
    const val target_sdk = 30
    val java_version = JavaVersion.VERSION_1_8
    const val jvm_target = "1.8"
}

object Releases {
    const val version_code = 1
    const val version_name = "1.0"
}

object Modules {
    const val app = ":app"
    const val core = ":core"
    const val model = ":model"
    const val repository = ":repository"
    const val utils = ":utils"
    //Features
    const val historyScreen = ":historyScreen"
}

object Versions {
    //Design
    const val appcompat = "1.3.1"
    const val material = "1.4.0"
    const val constraintlayout = "2.1.0"

    //Kotlin
    const val core = "1.6.0"
    const val coroutinesCore = "1.5.1"
    const val coroutinesAndroid = "1.5.1"

    //Retrofit
    const val retrofit = "2.9.0"
    const val converterGson = "2.9.0"
    const val interceptor = "5.0.0-alpha.2"
    const val adapterCoroutines = "0.9.2"

    //Koin
    const val koinAndroid = "2.0.1"
    const val koinViewModel = "2.0.1"

    //Coil
    const val coil = "0.11.0"

    //Room
    const val roomKtx = "2.4.0-alpha04"
    const val runtime = "2.4.0-alpha04"
    const val roomCompiler = "2.4.0-alpha04"

    //Google Play
    const val googlePlayCore = "1.10.1"

    //Test
    const val jUnit = "4.+"
    const val testJunit = "1.1.3"
    const val espressoCore = "3.4.0"
}


object Design {
    const val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
    const val material = "com.google.android.material:material:${Versions.material}"
    const val constraintlayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintlayout}"
}

object Kotlin {
    const val core = "androidx.core:core-ktx:${Versions.core}"
    const val coroutines_core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutinesCore}"
    const val coroutines_android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutinesAndroid}"
}

object Retrofit {
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val converter_gson = "com.squareup.retrofit2:converter-gson:${Versions.converterGson}"
    const val adapter_coroutines =
        "com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:${Versions.adapterCoroutines}"
    const val logging_interceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.interceptor}"
}

object Koin {
    const val koin_android = "org.koin:koin-android:${Versions.koinAndroid}"
    const val koin_view_model = "org.koin:koin-android-viewmodel:${Versions.koinViewModel}"
}

object Coil {
    const val coil = "io.coil-kt:coil:${Versions.coil}"
}

object Room {
    const val runtime = "androidx.room:room-runtime:${Versions.runtime}"
    const val compiler = "androidx.room:room-compiler:${Versions.roomCompiler}"
    const val room_ktx = "androidx.room:room-ktx:${Versions.roomKtx}"
}

object TestImpl {
    const val junit = "junit:junit:${Versions.jUnit}"
    const val test_junit = "androidx.test:runner:${Versions.testJunit}"
    const val espresso = "androidx.test.espresso:espresso-core:${Versions.espressoCore}"
}

object GooglePlay {
    const val googlePlayCore = "com.google.android.play:core:${Versions.googlePlayCore}"
}
