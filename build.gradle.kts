// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.kotlin.jvm) apply false
    alias(libs.plugins.kotlin.serialization) apply false
    alias(libs.plugins.kotlinAndroid) apply false
    alias(libs.plugins.com.android.library) apply false
    alias(libs.plugins.androidHilt) apply false
    alias(libs.plugins.ksp) apply false
    alias(libs.plugins.jupiterPlugin) apply false
    alias(libs.plugins.googleServices) apply false
}

