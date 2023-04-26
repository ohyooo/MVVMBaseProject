plugins {
    id("com.android.library")
    kotlin("android")
}

android {
    namespace = "com.ohyooo.lib"
    compileSdk = Ext.compileSdkVersion
    buildToolsVersion = Ext.buildToolsVersion
    defaultConfig {
        minSdk = Ext.minSdkVersion
        proguardFile("consumer-rules.pro")
    }
    buildTypes {
        debug {
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "consumer-rules.pro")
        }
        release {
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "consumer-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    buildFeatures {
        dataBinding = true
    }
}

dependencies {
    implementation(Libs.AndroidX.fragmentKtx)
}
