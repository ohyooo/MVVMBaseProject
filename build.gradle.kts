buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath(com.ohyooo.version.Libs.Plugin.AGP)
        classpath(com.ohyooo.version.Libs.Plugin.KGP)
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
        maven { url = uri("https://jitpack.io") }
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}