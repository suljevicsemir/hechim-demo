
plugins {
    id("hechimdemo.android.feature")
    id("hechimdemo.android.library.compose")
}

android {
    namespace = "com.semirsuljevic.ui"
}

dependencies {

    implementation(libs.core.ktx)
    implementation(libs.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.espresso.core)
}
