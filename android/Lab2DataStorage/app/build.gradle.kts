plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.example.datastorage"
    compileSdk = 35

    signingConfigs {
        create("lab2debug") {
            storeFile = file("../../lab2-debug.keystore")
            storePassword = "android"
            keyAlias = "androiddebugkey"
            keyPassword = "android"
        }
    }

    defaultConfig {
        applicationId = "com.example.datastorage"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
    }

    buildTypes {
        debug {
            signingConfig = signingConfigs.getByName("lab2debug")
        }
        release {
            isMinifyEnabled = false
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}
