import com.ohyooo.version.Libs

plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
}

android {
    compileSdkVersion(com.ohyooo.version.Ext.compileSdkVersion)
    buildToolsVersion(com.ohyooo.version.Ext.buildToolsVersion)
    defaultConfig {
        minSdkVersion(com.ohyooo.version.Ext.minSdkVersion)
        targetSdkVersion(com.ohyooo.version.Ext.targetSdkVersion)
        versionCode(com.ohyooo.version.Ext.versionCode)
        versionName(com.ohyooo.version.Ext.versionName)
        consumerProguardFiles("consumer-rules.pro")
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
        useIR = true
    }
    buildFeatures {
        dataBinding = true
    }
}

dependencies {
    implementation(Libs.AndroidX.fragmentKtx)
}
