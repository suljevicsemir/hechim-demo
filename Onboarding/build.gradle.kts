@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    id("hechimdemo.android.feature")
    id("hechimdemo.android.library.compose")
    alias(libs.plugins.kotlin.serialization)
//    alias(libs.plugins.com.android.library)
//    alias(libs.plugins.kotlinAndroid)
}

android {
    namespace = "com.cmtelematics.cmtauthentication"
    hilt {
        enableAggregatingTask = true
    }
}

dependencies {

    implementation(libs.core.ktx)
    implementation(libs.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.espresso.core)
}
