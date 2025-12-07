// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.compose) apply false

    //Dagger hilt
    // ✅ Dagger Hilt: Use the alias defined in libs.versions.toml
    alias(libs.plugins.hilt.android) apply false


    // Android library plugin for the data module
//    alias(libs.plugins.android.library) apply false
//    alias(libs.plugins.kotlin.jvm) apply false
    alias(libs.plugins.jetbrains.kotlin.jvm) apply false
}