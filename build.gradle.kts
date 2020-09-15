import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
  val kotlinVersion = "1.3.70"

  id("org.springframework.boot") version "2.3.3.RELEASE"
  id("io.spring.dependency-management") version "1.0.10.RELEASE"
  kotlin("jvm") version kotlinVersion
  kotlin("kapt") version kotlinVersion
  kotlin("plugin.jpa") version "1.3.72"
  kotlin("plugin.allopen") version kotlinVersion
  kotlin("plugin.spring") version kotlinVersion
}

allprojects {
  repositories {
    mavenCentral()
    maven { url = uri("https://plugins.gradle.org/m2/") }
  }
}

dependencies {
  developmentOnly("org.springframework.boot:spring-boot-devtools")
}

subprojects {
  group = "kr.latera"
  version = "0.0.1-SNAPSHOT"

  apply(plugin = "java")
  apply(plugin = "kotlin")
  apply(plugin = "kotlin-allopen")
  apply(plugin = "kotlin-jpa")
  apply(plugin = "kotlin-kapt")
  apply(plugin = "kotlin-noarg")

  apply(plugin = "io.spring.dependency-management")
  apply(plugin = "org.springframework.boot")

  java.sourceCompatibility = JavaVersion.VERSION_11

  allOpen {
    annotation("javax.persistence.Entity")
    annotation("javax.persistence.MappedSuperclass")
    annotation("org.springframework.stereotype.Component")
    annotation("org.springframework.stereotype.Controller")
    annotation("org.springframework.stereotype.Service")
    annotation("org.springframework.stereotype.Repository")
    annotation("org.springframework.stereotype.Configuration")
    annotation("javax.persistence.Embeddable")
  }

  dependencies {
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.boot:spring-boot-starter-data-r2dbc")
    implementation("org.springframework.boot:spring-boot-starter-thymeleaf")
    implementation("org.springframework.boot:spring-boot-starter-webflux")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("io.projectreactor.kotlin:reactor-kotlin-extensions")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")
    runtimeOnly("com.h2database:h2")
    runtimeOnly("io.micrometer:micrometer-registry-prometheus")
    runtimeOnly("io.r2dbc:r2dbc-h2")

    testImplementation("org.springframework.boot:spring-boot-starter-test") {
      exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
    }
    testImplementation("io.projectreactor:reactor-test")
  }

  tasks.withType<Test> {
    useJUnitPlatform()
  }

  tasks.withType<KotlinCompile> {
    kotlinOptions {
      freeCompilerArgs = listOf("-Xjsr305=strict")
//      jvmTarget = "1.8"
      jvmTarget = "11"
    }
  }
}