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


tasks.register<UpdateTask>("update")

abstract class UpdateTask : DefaultTask() {
    private val MAVEN_CENTRAL_URL = "https://repo1.maven.org/maven2/"
    private val GOOGLE_URL = "https://dl.google.com/dl/android/maven2/"

    private val versionRegex = "(?<=<version>)(.*\\n?)(?=</version>)".toRegex()
    private val numberDotRegex = "^(\\d+\\.)?(\\d+\\.)?(\\*|\\d+)\$".toRegex()

    @TaskAction
    fun update() {
        val stable = false
        val size = Libs.deps.size
        Libs.deps.forEachIndexed i@{ index, dep ->
            println("$index/$size")
            val vargs = dep.split(":")

            val url = StringBuffer(GOOGLE_URL)
            // val url = if (dep.contains("androidx") || dep.contains("google") || dep.contains("com.android.tools.build:gradle")) {
            //     StringBuffer(GOOGLE_URL)
            // } else {
            //     StringBuffer(MAVEN_CENTRAL_URL)
            // }

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
                java.net.URL(url.toString()).readText()
            } catch (e: Exception) {
                if (url.contains(GOOGLE_URL)) {
                    java.net.URL(url.toString().replace(GOOGLE_URL, MAVEN_CENTRAL_URL))
                } else {
                    java.net.URL(url.toString().replace(MAVEN_CENTRAL_URL, GOOGLE_URL))
                }.readText()
            }

            val latestVersion = mutableListOf<String>()
            xml.split("\n").forEach { l ->
                versionRegex.find(l)?.value?.let {
                    latestVersion.add(it)
                }
            }

            if (latestVersion.isEmpty()) {
                println("eeeeee $dep latest version=$latestVersion")
                return@i
            }

            val lastStable = try {
                if (stable) {
                    latestVersion.last { numberDotRegex.matches(it) }
                } else {
                    latestVersion.last()
                }
            } catch (e: Exception) {
                latestVersion.last()
            }

            if (Version.build(lastStable).compare(Version.build(vargs.last())) > 0) {
                println("eeeeee $dep has update for $lastStable")
            }
        }
    }


    private data class Version(
        val a: Int,
        val b: Int,
        val c: Int,
        val r: String,
        val f: Int,
    ) {
        fun compare(v: Version): Int {
            if (a != v.a) return a.compareTo(v.a)
            if (b != v.b) return b.compareTo(v.b)
            if (c != v.c) return c.compareTo(v.c)
            if (r != v.r) r.int.compareTo(v.r.int)
            if (f != v.f) return f.compareTo(v.f)
            return 0
        }

        companion object {
            private const val STABLE = "stable"
            private const val BETA = "beta"
            private const val ALPHA = "alpha"
            private val releaseType = arrayOf(STABLE, BETA, ALPHA)
            private val releaseRegex = "$STABLE|$BETA|$ALPHA".toRegex()

            private val String.int: Int
                get() = when (this) {
                    BETA -> 1
                    ALPHA -> 2
                    else -> 0
                }

            fun build(s: String): Version {
                val list = s.split(".")

                if (list.size == 3) {
                    val a = list[0].toIntOrNull() ?: 0
                    val b = list[1].toIntOrNull() ?: 0

                    val c = list[2].toIntOrNull()
                    if (c != null) {
                        return Version(a, b, c, "", 0)
                    } else {
                        val rf = list[2]

                        val splitList = rf.split("-")

                        if (splitList.size != 2) return Version(0, 0, 0, "", 0)

                        val r = when {
                            rf.contains(STABLE) -> STABLE
                            rf.contains(BETA) -> BETA
                            rf.contains(ALPHA) -> ALPHA
                            else -> ""
                        }

                        val cc = splitList[0].toIntOrNull() ?: 0
                        val f = splitList[1].replace(r, "").toIntOrNull() ?: 0

                        return Version(a, b, cc, r, f)
                    }
                }

                return Version(0, 0, 0, "", 0)
            }
        }
    }

}