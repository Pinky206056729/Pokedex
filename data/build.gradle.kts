plugins{
    id("com.android.library")
    id("org.jetbrains.kotlin.android")

    // Hilt plugin (may apply KAPT, but not always sufficient)
    alias(libs.plugins.hilt.android)

    // 💡 CRITICAL FIX: Explicitly apply the Kotlin KAPT plugin
    kotlin("kapt")


}

android{
    namespace = "com.siphokazi.pokedex.data"
    compileSdk = 35

    defaultConfig {
        minSdk = 24
        targetSdk = 34

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    // 💡 Set the Kotlin target to match the Java target (1.8)
    kotlinOptions {
        jvmTarget = "1.8"
    }

}

dependencies {
    // 💡 Data depends on Domain (The most important rule!)
    implementation(project(":domain"))

    // External dependencies are isolated here
    implementation(libs.retrofit)
    implementation(libs.converter.gson)

    // 💡 ADD/VERIFY THESE IMPLEMENTATION DEPENDENCIES:

    // Retrofit Core Library
    implementation(libs.retrofit.core) // Assumes you named it 'retrofit.core'

    // Optional: Logging Interceptor for debugging network calls
    implementation(libs.okhttp.logging) // Assumes you named it 'okhttp.logging'

    // Hilt/DI libraries are also needed here for binding the RepositoryImpl
    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)

    implementation ("javax.inject:javax.inject:1")
}