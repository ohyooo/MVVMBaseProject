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
    }

    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions {
            jvmTarget = "17"
            freeCompilerArgs = freeCompilerArgs + listOf("-opt-in=kotlin.RequiresOptIn", "-Xuse-k2", "-Xbackend-threads=12", "-Xcontext-receivers", "-jvm-target=17")
        }
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

        private const val checkStable = true
        private const val autoModify = true

        private val stableList = arrayOf(Libs.Plugin.KGP, Libs.Kotlin.stdlib)
    }

    @TaskAction
    fun update() {
        val file = if (autoModify) File("./buildSrc/src/main/kotlin/dependencies.kt") else null
        val text = arrayOf(file?.readText() ?: "")

        val size = Libs.deps.size
        Libs.deps.forEachIndexed i@{ index, dep ->
            println("${index + 1}/$size")
            check(dep, text)
        }

        file?.writeText(text[0])
    }

    fun check(dep: String, ktFile: Array<String>) {
        val lib = Library.parse(dep)
        if (lib == Library.empty) return
        val v1 = lib.v

        val url = GOOGLE_URL + lib.toURL
        val xml = fetchXML(url)
        val latest = getLatest(dep, xml) ?: return

        val v2 = Version.parse(latest)
        if (Version.compare(v1, v2) < 0) {
            println("eeeeee $dep has update for $latest")

            if (!autoModify) return

            val s = lib.toString
            if (ktFile[0].indexOf(s) > 0) {
                ktFile[0] = ktFile[0].replace(s, s.replace(lib.v.toString, v2.o))
            } else {
                var index = ktFile[0].indexOf(lib.toName) + lib.toName.length
                if (ktFile[0][++index] == '$') {
                    val sb = StringBuilder()
                    while (true) {
                        val char = ktFile[0][++index]
                        if (char != '"') {
                            sb.append(char)
                        } else {
                            val varName = sb.toString()
                            ktFile[0] = ktFile[0].replace("val $varName = \"${v1.o}\"", "val $varName = \"${v2.o}\"")
                            break
                        }
                    }
                }
            }
        }
    }

    private fun fetchXML(url: String) = try {
        java.net.URL(url).readText()
    } catch (e: Exception) {
        if (url.contains(GOOGLE_URL)) {
            java.net.URL(url.replace(GOOGLE_URL, MAVEN_CENTRAL_URL))
        } else {
            java.net.URL(url.replace(MAVEN_CENTRAL_URL, GOOGLE_URL))
        }.readText()
    }

    private fun getLatest(dep: String, xml: String, stable: Boolean = checkStable): String? {
        val latestVersion = mutableListOf<String>()

        xml.split("\n").forEach { l ->
            versionRegex.find(l)?.value?.let {
                latestVersion.add(it)
            }
        }

        if (latestVersion.isEmpty()) {
            println("eeeeee $dep latest version=$latestVersion")
            return null
        }

        return try {
            if (stable || stableList.contains(dep)) {
                latestVersion.last { numberDotRegex.matches(it) }
            } else {
                latestVersion.last()
            }
        } catch (e: Exception) {
            latestVersion.last()
        }
    }

    private data class Library(
        val g: String,
        val n: String,
        val v: Version,
    ) {
        val toString get() = "$g:$n:${v.toString}"

        val toName get() = "$g:$n"

        val toURL
            get() = StringBuffer("").apply {
                append(g.replace(".", "/"))
                append("/")
                append(n)
                append("/maven-metadata.xml")
            }.toString()

        companion object {
            val empty = Library("", "", Version.empty)

            fun parse(s: String): Library {
                val vargs = s.split(":")
                if (vargs.size != 3) return empty
                return Library(vargs[0], vargs[1], Version.parse(vargs[2]))
            }
        }
    }

    private data class Version(
        val a: Int,
        val b: Int,
        val c: Int,
        val r: String, // release type
        val f: Int, // release type to int
        val o: String, // original string
    ) {
        val toString get() = "$a.$b.$c${if (r.isNotBlank() && f > -1) "-$r${"$f".padStart(2, '0')}" else if (r.isNotBlank()) "-$r" else ""}"

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
                if (v1.r != v2.r) return v1.r.int.compareTo(v2.r.int)
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
                            else -> rf.subSequence(rf.indexOf("-") + 1, rf.length).toString()
                        }

                        val cc = splitList[0].toIntOrNull() ?: 0
                        val f = splitList[1].replace(r, "").toIntOrNull() ?: -1

                        return Version(a, b, cc, r, f, s)
                    }
                }

                return empty
            }
        }
    }

}
