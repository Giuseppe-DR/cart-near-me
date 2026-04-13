plugins {
    kotlin("jvm")
    id("application")
    alias(libs.plugins.ktor)
    alias(libs.plugins.kotlin.serialization)
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}

application {
    mainClass.set("com.cartnearme.server.ApplicationKt")
}

dependencies {
    implementation(project(":shared"))
    
    implementation(libs.ktor.server.core)
    implementation(libs.ktor.server.cio)
    implementation(libs.ktor.server.auth.jwt)
    implementation(libs.ktor.server.content.negotiation)
    implementation(libs.ktor.serialization.kotlinx.json)
    implementation(libs.ktor.server.status.pages)
    
    // Observability & Logging
    implementation(libs.ktor.server.call.logging)
    implementation(libs.ktor.server.metrics.micrometer)
    implementation(libs.micrometer.registry.prometheus)
    implementation(libs.logback.classic)
    implementation(libs.sentry.core)
    implementation(libs.sentry.logback)
    
    implementation(libs.koin.ktor)
    implementation(libs.sqldelight.driver.jvm)
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
    compilerOptions {
        jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_17)
    }
}
