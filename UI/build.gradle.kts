
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
    implementation(project(":Foundation"))
}
