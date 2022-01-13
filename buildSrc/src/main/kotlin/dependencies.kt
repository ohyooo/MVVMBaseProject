
object Ext {
    const val applicationId = "com.ohyooo.demo"
    const val minSdkVersion = 16
    const val compileSdkVersion = 32
    const val buildToolsVersion = "32.0.0"
    const val targetSdkVersion = 32
    const val versionCode = 1
    const val versionName = "1.0"
}

object Libs {

    private const val kotlin_version = "1.6.10"

    object Plugin {
        const val AGP = "com.android.tools.build:gradle:7.2.0-alpha07"
        const val KGP = "org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlin_version"
    }

    object Kotlin {
        const val stdlib = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:$kotlin_version"
        const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.0"
    }

    object AndroidX {
        const val appcompat = "androidx.appcompat:appcompat:1.4.1"
        const val coreKtx = "androidx.core:core-ktx:1.8.0-alpha02"
        const val fragmentKtx = "androidx.fragment:fragment-ktx:1.4.0-alpha09"
        const val constraintLayout = "androidx.constraintlayout:constraintlayout:2.1.2"
        const val recyclerview = "androidx.recyclerview:recyclerview:1.3.0-alpha01"
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
        AndroidX.recyclerview,
        Squareup.retrofit,
        Squareup.converter,
        Squareup.log,
        Third.timber,
    )
}
