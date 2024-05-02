
plugins {
    id("hechimdemo.android.feature")
    id("hechimdemo.android.library.compose")
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.hechimdemo.onboarding"
    hilt {
        enableAggregatingTask = true
    }
}

dependencies {
    implementation(project(":Foundation"))
    implementation(project(":UI"))
    implementation(libs.core.ktx)
    implementation(libs.appcompat)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.espresso.core)
}
