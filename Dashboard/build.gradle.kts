plugins {
    id("hechimdemo.android.feature")
    id("hechimdemo.android.library.compose")
}

android {
    namespace = "com.hechimdemo.dashboard"
}

dependencies {
    implementation(project(":UI"))
    implementation(project(":Onboarding"))
    implementation(project(":Foundation"))
    implementation(libs.core.ktx)
    implementation(libs.appcompat)
    implementation(libs.material)
}
