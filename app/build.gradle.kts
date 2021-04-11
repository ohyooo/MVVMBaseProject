import com.ohyooo.version.Ext
import com.ohyooo.version.Libs

plugins {
    id ("com.android.application")
    kotlin ("android")
    kotlin ("kapt")
    id ("kotlin-parcelize")
}

android {
    signingConfigs {
        getByName("debug") {
            storeFile = file("..\\signkey.jks")
            storePassword = "123456"
            keyPassword = "123456"
            keyAlias = "demo"

            enableV3Signing = true
            enableV4Signing = true
        }
    }
    compileSdkVersion(Ext.compileSdkVersion)
    buildToolsVersion(Ext.buildToolsVersion)
    defaultConfig {
        applicationId(Ext.applicationId)
        minSdkVersion(Ext.minSdkVersion)
        targetSdkVersion(Ext.targetSdkVersion)
        versionCode(Ext.versionCode)
        versionName(Ext.versionName)
        consumerProguardFiles("consumer-rules.pro")
        signingConfig = signingConfigs.getByName("debug")
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
        viewBinding = true
        dataBinding = true
    }
}


dependencies {
    implementation (project(":lib"))
    implementation (project(":network"))
    implementation (Libs.AndroidX.appcompat)
    implementation (Libs.AndroidX.coreKtx)
    implementation (Libs.AndroidX.fragmentKtx)
    implementation (Libs.AndroidX.constraintLayout)
    //
    implementation (Libs.Kotlin.stdlib)
    //
    implementation (Libs.Third.timber)
}
