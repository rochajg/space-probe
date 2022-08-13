rootProject.name = "space-probe"

pluginManagement {
    val springVersion: String by settings
    val springDependencyManagementVersion: String by settings
    val kotlinVersion: String by settings
    val ktlintPluginVersion: String by settings
    val koverVersion: String by settings

    plugins {
        id("org.springframework.boot") version springVersion
        id("io.spring.dependency-management") version springDependencyManagementVersion
        id("org.jlleitschuh.gradle.ktlint") version ktlintPluginVersion
        id("org.jetbrains.kotlinx.kover") version koverVersion
        kotlin("jvm") version kotlinVersion
        kotlin("plugin.spring") version kotlinVersion
    }
}
