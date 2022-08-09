import com.google.gson.Gson
import com.google.gson.JsonObject
import com.matthewprenger.cursegradle.CurseArtifact
import com.matthewprenger.cursegradle.CurseProject
import com.matthewprenger.cursegradle.CurseRelation
import java.io.InputStreamReader
import java.time.Instant
import java.time.format.DateTimeFormatter

fun property(key: String) = project.findProperty(key).toString()
fun hasProperty(key: String) = project.hasProperty(key) && property(key) != null

plugins {
    id("java")
    id("net.minecraftforge.gradle")
    id("org.parchmentmc.librarian.forgegradle")
    id("idea")
    id("maven-publish")
    id("com.matthewprenger.cursegradle") version "1.4.0"
}

repositories {
    maven("https://ldtteam.jfrog.io/ldtteam/modding/")
    maven("https://maven.tehnut.info")
    maven("https://www.cursemaven.com") {
        content {
            includeGroup("curse.maven")
        }
    }
    maven("https://harleyoconnor.com/maven")
    maven("https://squiddev.cc/maven/")
    maven("https://maxanier.de/maven2")
}

val modName = property("modName")
val modId = property("modId")
val modVersion = property("modVersion")
val mcVersion = property("mcVersion")

version = "$mcVersion-$modVersion"
group = property("group")

minecraft {
    mappings("parchment", "${property("mappingsVersion")}-$mcVersion")

    runs {
        create("client") {
            workingDirectory = file("run").absolutePath

            property("forge.logging.markers", "SCAN,REGISTRIES,REGISTRYDUMP")
            property("forge.logging.console.level", "debug")

            property("mixin.env.remapRefMap", "true")
            property("mixin.env.refMapRemappingFile", "${buildDir}/createSrgToMcp/output.srg")

            if (project.hasProperty("mcUuid")) {
                args("--uuid", property("mcUuid"))
            }
            if (project.hasProperty("mcUsername")) {
                args("--username", property("mcUsername"))
            }
            if (project.hasProperty("mcAccessToken")) {
                args("--accessToken", property("mcAccessToken"))
            }

            mods {
                create(modId) {
                    source(sourceSets.main.get())
                }
            }
        }

        create("server") {
            workingDirectory = file("run").absolutePath

            property("forge.logging.markers", "SCAN,REGISTRIES,REGISTRYDUMP")
            property("forge.logging.console.level", "debug")

            property("mixin.env.remapRefMap", "true")
            property("mixin.env.refMapRemappingFile", "${buildDir}/createSrgToMcp/output.srg")

            mods {
                create(modId) {
                    source(sourceSets.main.get())
                }
            }
        }

        create("data") {
            workingDirectory = file("run").absolutePath

            property("forge.logging.markers", "SCAN,REGISTRIES,REGISTRYDUMP")
            property("forge.logging.console.level", "debug")

            property("mixin.env.remapRefMap", "true")
            property("mixin.env.refMapRemappingFile", "${buildDir}/createSrgToMcp/output.srg")

            args("--mod", modId, "--all", "--output", file("src/generated/resources/"))

            mods {
                create(modId) {
                    source(sourceSets.main.get())
                }
            }
        }
    }
}

sourceSets.main.get().resources {
    srcDir("src/generated/resources")
}

dependencies {
    minecraft("net.minecraftforge:forge:${mcVersion}-${property("forgeVersion")}")

    // Temp as TehNut Maven is down.
    implementation(fg.deobf("curse.maven:hwyla-253449:3033593"))
//    compileOnly(fg.deobf("mcp.mobius.waila:Hwyla:${property("hwylaVersion")}:api"))
//    runtimeOnly(fg.deobf("mcp.mobius.waila:Hwyla:${property("hwylaVersion")}"))

    compileOnly(fg.deobf("mezz.jei:jei-${mcVersion}:${property("jeiVersion")}:api"))
    runtimeOnly(fg.deobf("mezz.jei:jei-${mcVersion}:${property("jeiVersion")}"))

    implementation(fg.deobf("com.ferreusveritas.dynamictrees:DynamicTrees-${mcVersion}:${property("dynamicTreesVersion")}"))
    implementation(fg.deobf("de.teamlapen.vampirism:Vampirism:${mcVersion}-${property("vampirismVersion")}"))

    runtimeOnly(fg.deobf("com.harleyoconnor.suggestionproviderfix:SuggestionProviderFix:${mcVersion}-${property("suggestionProviderFixVersion")}"))
}

tasks.jar {
    manifest.attributes(
        "Specification-Title" to modName,
        "Specification-Vendor" to "Harley O'Connor",
        "Specification-Version" to "1",
        "Implementation-Title" to modName,
        "Implementation-Version" to project.version,
        "Implementation-Vendor" to "Harley O'Connor",
        "Implementation-Timestamp" to DateTimeFormatter.ISO_INSTANT.format(Instant.now())
    )

    archiveBaseName.set(modName)
    finalizedBy("reobfJar")
}

java {
    withSourcesJar()

    toolchain {
        languageVersion.set(JavaLanguageVersion.of(8))
    }
}


/* CurseForge uploading via CurseGradle */

/**
 * Reads changelog from [version_info.json](./version_info.json).
 */
fun readChangelog(): String? {
    val versionInfoFile = file("version_info.json")
    val jsonObject = Gson().fromJson(InputStreamReader(versionInfoFile.inputStream()), JsonObject::class.java)
    return jsonObject
        .get(mcVersion)?.asJsonObject
        ?.get(project.version.toString())?.asString
}

curseforge {
    if (!hasProperty("curseApiKey") || !hasProperty("curse.file.type") || !hasProperty("curse.id")) {
        project.logger.log(LogLevel.WARN, "API Key and file type for CurseForge not detected; uploading will be disabled.")
        return@curseforge
    }

    apiKey = property("curseApiKey")

    project {
        id = property("curse.id")

        addGameVersion(mcVersion)

        changelog = readChangelog() ?: "No changelog provided."
        changelogType = "markdown"
        releaseType = property("curse.file.type")

        mainArtifact(tasks.findByName("jar")) {
            displayName = project.version.toString()
            relations {
                requiredDependency("dynamictrees")
                requiredDependency("vampirism-become-a-vampire")
            }
        }

        addArtifact(tasks.findByName("sourcesJar")) {
            displayName = "${project.version} [Sources]"
        }
    }
}


/* Maven Publishing */

tasks.withType<GenerateModuleMetadata> {
    enabled = false
}

fun hasMavenAccess() = hasProperty("harleyOConnorMavenUsername") && hasProperty("harleyOConnorMavenPassword")

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            artifactId = modName
            version = modVersion

            from(components["java"])

            pom {
                name.set(modName)
                url.set("https://github.com/Harleyoc1/$modName")
                licenses {
                    license {
                        name.set("MIT")
                        url.set("https://mit-license.org")
                    }
                }
                developers {
                    developer {
                        id.set("Harleyoc1")
                        name.set("Harley O'Connor")
                        email.set("harleyoc1@gmail.com")
                    }
                }
                scm {
                    connection.set("scm:git:git://github.com/Harleyoc1/$modName.git")
                    developerConnection.set("scm:git:ssh://github.com/Harleyoc1/$modName.git")
                    url.set("https://github.com/Harleyoc1/$modName")
                }
            }

            pom.withXml {
                val element = asElement()

                // Clear dependencies.
                for (i in 0 until element.childNodes.length) {
                    val node = element.childNodes.item(i)
                    if (node?.nodeName == "dependencies") {
                        element.removeChild(node)
                    }
                }
            }
        }
    }
    repositories {
        maven("file:///${project.projectDir}/mcmodsrepo")

        if (!hasMavenAccess()) {
            logger.log(LogLevel.WARN, "Credentials for maven not detected; it will be disabled.")
            return@repositories
        }

        maven("https://harleyoconnor.com/maven") {
            name = "HarleyOConnor"
            credentials { // If you'd like to upload this to my maven feel free to ask for credentials.
                username = property("harleyOConnorMavenUsername")
                password = property("harleyOConnorMavenPassword")
            }
        }
    }
}

/**
 * Composite task that runs the CurseForge and maven upload tasks.
 */
tasks.register("publishToAll") {
    this.group = "publishing"
    this.dependsOn("curseforge")
    if (hasMavenAccess()) {
        this.dependsOn("publishMavenJavaPublicationToHarleyOConnorRepository")
    }
}


/* Extensions to make CurseGradle extension slightly neater. */

fun com.matthewprenger.cursegradle.CurseExtension.project(action: CurseProject.() -> Unit) {
    this.project(closureOf(action))
}

fun CurseProject.mainArtifact(artifact: Task?, action: CurseArtifact.() -> Unit) {
    this.mainArtifact(artifact, closureOf(action))
}

fun CurseArtifact.relations(action: CurseRelation.() -> Unit) {
    this.relations(closureOf(action))
}

fun CurseProject.addArtifact(artifact: Task?, action: CurseArtifact.() -> Unit) {
    this.addArtifact(artifact, closureOf(action))
}
