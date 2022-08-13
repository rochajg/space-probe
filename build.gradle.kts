import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val kotestVersion: String by project

plugins {
    id("org.springframework.boot")
    id("io.spring.dependency-management")
    id("org.jlleitschuh.gradle.ktlint")
    id("org.jetbrains.kotlinx.kover")
    kotlin("jvm")
    kotlin("plugin.spring")
}

group = "com.example"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-jdbc")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    runtimeOnly("mysql:mysql-connector-java")

    // TESTS
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("io.kotest:kotest-runner-junit5:$kotestVersion")
    runtimeOnly("com.h2database:h2") // for tests
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "11"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

kover {
    isDisabled.set(false) // true to disable instrumentation and all Kover tasks in this project
    engine.set(kotlinx.kover.api.DefaultIntellijEngine) // change Coverage Engine
    filters { // common filters for all default Kover tasks
        classes { // common class filter for all default Kover tasks
            includes += "com.example.*" // class inclusion rules
            excludes += listOf() // class exclusion rules
        }
    }
}

val installGitHook by tasks.registering(Copy::class) {
    from(file("${rootProject.rootDir}/hooks/pre-commit"))
    into(file("${rootProject.rootDir}/.git/hooks"))
    fileMode = 0b111111101 // 775
}

tasks.build {
    dependsOn(installGitHook)
}
