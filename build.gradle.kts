buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath(Libs.Plugin.AGP)
        classpath(Libs.Plugin.KGP)
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


val MAVEN_CENTRAL_URL = "https://repo.maven.apache.org/maven2/"
val GOOGLE_URL = "https://dl.google.com/dl/android/maven2/"

val latestRegex = "(?<=<latest>)(.*\\n?)(?=</latest>)".toRegex()

tasks.register("update", Delete::class) {
    Libs.deps.forEach { dep ->
        val vargs = dep.split(":")

        val url = if (dep.contains("androidx") || dep.contains("google") || dep.contains("com.android.tools.build:gradle")) {
            StringBuffer(GOOGLE_URL)
        } else {
            StringBuffer(MAVEN_CENTRAL_URL)
        }

        for (i in 0 until vargs.size - 1) {
            val w = vargs[i]

            when {
                i == 0 && w.contains(".") -> w.split(".").forEach { s ->
                    url.append(s).append("/")
                }
                else -> url.append(w).append("/")
            }
        }
        url.append("maven-metadata.xml")

        val xml = try {
            java.net.URL(url.toString())
        } catch (e: Exception) {
            if (url.contains(GOOGLE_URL)) {
                java.net.URL(url.toString().replace(GOOGLE_URL, MAVEN_CENTRAL_URL))
            } else {
                java.net.URL(url.toString().replace(MAVEN_CENTRAL_URL, GOOGLE_URL))
            }
        }.readText()

        val latestVersion = latestRegex.find(xml)?.value

        if (latestVersion == null) {
            println("eeeee latest version=$latestVersion")
            return@forEach
        }

        if (latestVersion != vargs.last()) {
            println("eeeee $dep has update for $latestVersion $url")
        }
    }
}
