plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.zettaient.copy"
    compileSdk {
        version = release(36)
    }

    defaultConfig {
        applicationId = "com.zettaient.copy"
        minSdk = 26
        targetSdk = 36

        // Check for properties from GitHub, otherwise default to v1.0.0
        val verCode = project.findProperty("versionCode")?.toString()?.toInt() ?: 10000
        val verName = project.findProperty("versionName")?.toString() ?: "1.0.0-local"

        versionCode = verCode
        versionName = verName

        println("Building version: $versionName ($versionCode)")

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}