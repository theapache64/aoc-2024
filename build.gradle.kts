plugins {
    kotlin("jvm") version "2.1.0"
    kotlin("plugin.allopen") version "2.1.0"
    id("org.jetbrains.kotlinx.benchmark") version "0.4.13"
}

group = "com.github.theapache64"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    implementation("org.jetbrains.kotlinx:kotlinx-benchmark-runtime:0.4.13")
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(20)
}

benchmark {
    targets {
        register("main")
    }
}

allOpen {
    annotation("org.openjdk.jmh.annotations.State")
}