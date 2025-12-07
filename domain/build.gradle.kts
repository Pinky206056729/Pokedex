plugins{
//    id("com.android.library")
//    id("org.jetbrains.kotlin.android")

    id("org.jetbrains.kotlin.jvm")
}

//android{
//    namespace = "com.siphokazi.pokedex.domain"
//    compileSdk = 35
//
//    defaultConfig {
//        minSdk = 24
//        targetSdk = 35
//
//        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
//    }
//}

dependencies {
    // Only standard Kotlin libraries, primarily coroutines for concurrency support.
    implementation(libs.kotlinx.coroutines.core)
    // Unit testing framework
    testImplementation(libs.junit)
    implementation ("javax.inject:javax.inject:1")
}