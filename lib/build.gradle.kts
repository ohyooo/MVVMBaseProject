plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
}

android {
    compileSdk = Ext.compileSdkVersion
    buildToolsVersion = Ext.buildToolsVersion
    defaultConfig {
        minSdk = Ext.minSdkVersion
        targetSdk = Ext.targetSdkVersion
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        dataBinding = true
    }
}

dependencies {
    implementation(Libs.AndroidX.fragmentKtx)
}