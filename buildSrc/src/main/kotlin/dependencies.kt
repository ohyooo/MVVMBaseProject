private const val kotlin_version = "1.5.21"

object Ext {
    const val applicationId = "com.ohyooo.demo"
    const val minSdkVersion = 16
    const val compileSdkVersion = 31
    const val buildToolsVersion = "31.0.0"
    const val targetSdkVersion = 31
    const val versionCode = 1
    const val versionName = "1.0"
}

object Libs {

    object Plugin {
        const val AGP = "com.android.tools.build:gradle:7.1.0-alpha08"
        const val KGP = "org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlin_version"
    }

    object Kotlin {
        const val stdlib = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:$kotlin_version"
        const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-android:1.5.1"
    }

    object AndroidX {
        const val appcompat = "androidx.appcompat:appcompat:1.4.0-alpha03"
        const val coreKtx = "androidx.core:core-ktx:1.7.0-alpha01"
        const val fragmentKtx = "androidx.fragment:fragment-ktx:1.4.0-alpha07"
        const val constraintLayout = "androidx.constraintlayout:constraintlayout:2.1.0"
    }

    object Squareup {
        private const val retrofit2_version = "2.9.0"
        const val retrofit = "com.squareup.retrofit2:retrofit:$retrofit2_version"
        const val converter = "com.squareup.retrofit2:converter-gson:$retrofit2_version"
        const val log = "com.squareup.okhttp3:logging-interceptor:5.0.0-alpha.2"
    }

    object Third {
        const val timber = "com.jakewharton.timber:timber:5.0.1"
    }

    val deps = arrayOf(
        Plugin.AGP,
        Plugin.KGP,
        Kotlin.stdlib,
        Kotlin.coroutines,
        AndroidX.appcompat,
        AndroidX.coreKtx,
        AndroidX.fragmentKtx,
        AndroidX.constraintLayout,
        Squareup.retrofit,
        Squareup.converter,
        Squareup.log,
        Third.timber,
    )
}
