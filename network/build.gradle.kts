
plugins {
    id("com.android.library")
}

android {
    namespace = "com.ohyooo.network"
    compileSdk {
        version = release(libs.versions.compile.sdk.get().toInt()) {
            minorApiLevel = libs.versions.compile.minor.get().toInt()
        }
    }
    defaultConfig {
        minSdk = libs.versions.min.sdk.get().toInt()
        consumerProguardFiles("consumer-rules.pro")
    }
    buildTypes {
        debug {
            isMinifyEnabled = false
        }
        release {
            isMinifyEnabled = true
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
    }
}

dependencies {
    implementation(libs.coroutines)
    //
    implementation(libs.retrofit)
    implementation(libs.converter)
    implementation(libs.log)
}
