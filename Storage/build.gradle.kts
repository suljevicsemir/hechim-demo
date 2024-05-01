
plugins {
    id("hechimdemo.android.feature")
    id("hechimdemo.android.library.compose")
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.hechimdemo.storage"
    hilt {
        enableAggregatingTask = true
    }
}

dependencies {

    implementation(libs.core.ktx)
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.security.crypto)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.espresso.core)
}
