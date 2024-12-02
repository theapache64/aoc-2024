plugins {
    kotlin("jvm") version "2.1.0"
    kotlin("plugin.allopen") version "2.0.21"
    id("org.jetbrains.kotlinx.benchmark") version "0.4.13"
}

group = "com.github.theapache64"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.jetbrains.kotlin:kotlin-test")
    implementation("org.jetbrains.kotlinx:kotlinx-benchmark-runtime:0.4.13")
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(11)
}

benchmark {
    targets {
        register("jvm")
    }
}

allOpen {
    annotation("org.openjdk.jmh.annotations.State")
}