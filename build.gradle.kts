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
    companion object {
        private const val MAVEN_CENTRAL_URL = "https://repo1.maven.org/maven2/"
        private const val GOOGLE_URL = "https://dl.google.com/dl/android/maven2/"

        private val versionRegex = "(?<=<version>)(.*\\n?)(?=</version>)".toRegex()
        private val numberDotRegex = "^(\\d+\\.)?(\\d+\\.)?(\\*|\\d+)\$".toRegex()
    }

    @TaskAction
    fun update() {
        val stable = false
        val size = Libs.deps.size
        Libs.deps.forEachIndexed i@{ index, dep ->
            println("${index + 1}/$size")

            val lib = Library.parse(dep)

            val url = StringBuffer(GOOGLE_URL).append(lib.toURL)

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

            val v1 = Version.parse(lastStable)
            val v2 = lib.v
            if (Version.compare(v1, v2) > 0) {
                println("eeeeee $dep has update for $lastStable")
            }
        }
    }

    private data class Library(
        val g: String,
        val n: String,
        val v: Version,
    ) {
        val toURL: String
            get() = StringBuffer("").apply {
                append(g.replace(".", "/"))
                append("/")
                append(n)
                append("/maven-metadata.xml")
            }.toString()

        companion object {
            fun parse(s: String): Library {
                val vargs = s.split(":")
                if (vargs.size != 3) return Library("", "", Version.empty)
                return Library(vargs[0], vargs[1], Version.parse(vargs[2]))
            }
        }
    }

    private data class Version(
        val a: Int,
        val b: Int,
        val c: Int,
        val r: String,
        val f: Int,
        val o: String,
    ) {
        companion object {
            private const val STABLE = "stable"
            private const val BETA = "beta"
            private const val ALPHA = "alpha"
            private const val RC = "RC"
            private val releaseType = arrayOf(STABLE, RC, BETA, ALPHA)
            private val releaseRegex = "$STABLE|$BETA|$ALPHA".toRegex()

            val empty = Version(0, 0, 0, "", 0, "")

            private val String.int: Int
                get() = when (this) {
                    BETA -> 1
                    ALPHA -> 2
                    else -> 0
                }

            fun compare(v1: Version, v2: Version): Int {
                if (v1.a != v2.a) return v1.a.compareTo(v2.a)
                if (v1.b != v2.b) return v1.b.compareTo(v2.b)
                if (v1.c != v2.c) return v1.c.compareTo(v2.c)
                if (v1.r != v2.r) v1.r.int.compareTo(v2.r.int)
                if (v1.f != v2.f) return v1.f.compareTo(v2.f)
                return 0
            }

            fun parse(s: String): Version {
                val list = s.split(".")

                if (list.size == 3) {
                    val a = list[0].toIntOrNull() ?: 0
                    val b = list[1].toIntOrNull() ?: 0

                    val c = list[2].toIntOrNull()
                    if (c != null) {
                        return Version(a, b, c, "", 0, s)
                    } else {
                        val rf = list[2]

                        val splitList = rf.split("-")

                        if (splitList.size < 2) return empty

                        val r = when {
                            rf.contains(STABLE) -> STABLE
                            rf.contains(RC) -> RC
                            rf.contains(BETA) -> BETA
                            rf.contains(ALPHA) -> ALPHA
                            else -> ""
                        }

                        val cc = splitList[0].toIntOrNull() ?: 0
                        val f = splitList[1].replace(r, "").toIntOrNull() ?: 0

                        return Version(a, b, cc, r, f, s)
                    }
                }

                return empty
            }
        }
    }

}
