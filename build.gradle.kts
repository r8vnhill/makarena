/*
 * "Makarena" (c) by R8V
 * "Makarena" is licensed under a
 * Creative Commons Attribution 4.0 International License.
 * You should have received a copy of the license along with this
 * work. If not, see <https://creativecommons.org/licenses/by/4.0/>.
 */
@file:Suppress("SpellCheckingInspection")

import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.7.20"
    id("com.diffplug.spotless") version "6.10.0"
    id("org.jetbrains.compose") version "1.2.0"
}

group = "cl.ravenhill"
version = "0.2-ALPHA"

repositories {
    mavenCentral()
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    google()
}

dependencies {
    // GUI
    implementation(compose.desktop.currentOs)
    // LOGGING
    implementation("org.slf4j:slf4j-api:2.0.3")
    implementation("ch.qos.logback:logback-classic:1.4.3")
    implementation("ch.qos.logback:logback-core:1.4.3")
    implementation("io.kotest:kotest-framework-datatest:5.5.0")
    // TESTING
    testImplementation("io.kotest:kotest-runner-junit5:5.5.0")
    implementation("io.kotest:kotest-assertions-core:5.5.0")
    testImplementation("io.kotest:kotest-property:5.5.0")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.7.20")
    testImplementation("io.kotest:kotest-runner-junit5-jvm:5.5.0")

}

spotless {
    kotlin {
        diktat()//.configFile("diktat-analysis.yml")
    }
}

tasks.test {
    useJUnitPlatform()
}

val compileKotlin: KotlinCompile by tasks
compileKotlin.kotlinOptions {
    jvmTarget = "1.8"
}

compose.desktop {
    application {
        mainClass = "cl.ravenhill.makarena.gui.GameViewKt"
    }
}