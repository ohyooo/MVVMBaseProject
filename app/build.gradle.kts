@file:Suppress("UnstableApiUsage")

plugins {
    id("com.android.application")
}
val gitVersion = if (!File(rootDir.path + "/.git").exists()) {
    ""
} else {
    providers.exec {
        commandLine("git", "rev-parse", "--short", "HEAD")
        workingDir = rootDir
    }.standardOutput.asText.map { output ->
        output.trim().takeIf { it.isNotEmpty() }?.let { "-$it" } ?: ""
    }.getOrElse("")
}

android {
    signingConfigs {
        getByName("debug") {
            storeFile = file("signkey.jks")
            storePassword = "123456"
            keyPassword = "123456"
            keyAlias = "demo"

            enableV3Signing = true
            enableV4Signing = true
        }
    }
    namespace = libs.versions.application.id.get()
    compileSdk = libs.versions.compile.sdk.get().toInt()
    defaultConfig {
        applicationId = libs.versions.application.id.get()
        minSdk = libs.versions.min.sdk.get().toInt()
        targetSdk = libs.versions.target.sdk.get().toInt()
        versionCode = libs.versions.version.code.get().toInt()
        versionName = libs.versions.target.sdk.get() + gitVersion
        proguardFile("proguard-rules.pro")
        signingConfig = signingConfigs.getByName("debug")
    }
    buildTypes {
        release {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
    }
    buildFeatures {
        viewBinding = true
        dataBinding = true
    }
}


dependencies {
    implementation(project(":lib"))
    implementation(project(":network"))
    implementation(libs.appcompat)
    implementation(libs.coreKtx)
    implementation(libs.fragmentKtx)
    implementation(libs.constraintLayout)
    implementation(libs.recyclerview)
    //
    implementation (libs.timber)
}
