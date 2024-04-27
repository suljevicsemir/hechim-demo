import java.io.FileInputStream
import java.util.Properties


plugins {
    id("hechimdemo.android.application")
    id("hechimdemo.android.application.compose")
    id("hechimdemo.android.hilt")

}

//load signing values into variable, use to create signing config later
val keystorePropertiesFile: File = rootProject.file("keystore.properties")
val keystoreProperties = Properties()
keystoreProperties.load(FileInputStream(keystorePropertiesFile))

object SigningConstants {
    const val devStorePassword = "devStorePassword"
    const val devKeyPassword = "devAlias"
    const val devKeyAlias = "devKeyAlias"
    const val devStoreFile = "devStoreFile"

    const val prodStorePassword = "prodStorePassword"
    const val prodKeyPassword = "prodKeyPassword"
    const val prodKeyAlias = "prodKeyAlias"
    const val prodStoreFile = "prodStoreFile"

    const val devFlavor = "dev"
    const val prodFlavor = "prod"
    const val flavorDimension = "demo"
}



android {
    namespace = "com.example.hechimdemo"
    compileSdk = 34

    defaultConfig {

        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    signingConfigs {
        create(SigningConstants.devFlavor) {
            storeFile = file(SigningConstants.devStoreFile)
            storePassword = keystoreProperties.getProperty(SigningConstants.devStorePassword)
            keyAlias = keystoreProperties.getProperty(SigningConstants.devKeyAlias)
            keyPassword = keystoreProperties.getProperty(SigningConstants.devKeyPassword)
        }
        create(SigningConstants.prodFlavor) {
            storeFile = file(SigningConstants.prodStoreFile)
            storePassword = keystoreProperties.getProperty(SigningConstants.prodStorePassword)
            keyAlias = keystoreProperties.getProperty(SigningConstants.prodKeyAlias)
            keyPassword = keystoreProperties.getProperty(SigningConstants.prodKeyPassword)
        }
    }


    flavorDimensions.add(SigningConstants.flavorDimension)
    productFlavors {
        create(SigningConstants.devFlavor) {
            dimension = flavorDimensions.first()
            applicationId = "com.semirsuljevic.hechim"
            applicationIdSuffix = ".dev"
            buildConfigField("String", "BASE_URL", "\"TBD\"")
            signingConfig = signingConfigs.getAt(SigningConstants.devFlavor)
        }

        create(SigningConstants.prodFlavor) {
            dimension = flavorDimensions.first()
            applicationId = "ba.semirsuljevic.hechim"
            buildConfigField("String", "BASE_URL", "\"TBD\"")
            signingConfig = signingConfigs.getAt(SigningConstants.prodFlavor)
        }
    }



    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(project(":Onboarding"))
    implementation(project(":UI"))
    implementation(libs.core.ktx)
    implementation(libs.lifecycle.runtime.ktx)
    implementation(libs.lifecycle.viewmodel.ktx)
    implementation(libs.lifecycle.viewmodel.compose)
    implementation(libs.lifecycle.runtime.compose)
    testImplementation(libs.lifecycle.runtime.testing)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.espresso.core)
    androidTestImplementation(libs.ui.test.junit4)
    debugImplementation(libs.ui.tooling)
    debugImplementation(libs.ui.test.manifest)
    kaptTest(libs.hilt.compiler)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.espresso.core)
}
