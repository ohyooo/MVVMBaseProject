import com.ohyooo.version.Libs

plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
    id("kotlin-parcelize")
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
}

dependencies {
    implementation(Libs.Kotlin.coroutines)
    //
    implementation(Libs.Squareup.retrofit)
    implementation(Libs.Squareup.converter)
    implementation(Libs.Squareup.log)
}
