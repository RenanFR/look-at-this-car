// https://mvnrepository.com/artifact/org.springframework.cloud/spring-cloud-dependencies
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "2.6.0"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    kotlin("jvm") version "1.6.21"
    kotlin("plugin.spring") version "1.6.21"
    kotlin("plugin.noarg") version "1.4.32"
    kotlin("plugin.jpa") version "1.4.32"
    kotlin("kapt") version "1.5.20"
}

group = "br.com.lookatthiscar"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_18

repositories {
    mavenCentral()
    maven {
        url = uri("https://agama-270167558056.d.codeartifact.us-east-1.amazonaws.com/maven/agama/")
        credentials {
            username = "aws"
            password = System.getenv("CODEARTIFACT_AUTH_TOKEN")
        }
    }
}

ext {
    set("springCloudVersion", "2021.0.3")
}

dependencyManagement {
    imports {
        mavenBom("org.springframework.cloud:spring-cloud-dependencies:${property("springCloudVersion")}")
    }
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    testImplementation("org.springframework.boot:spring-boot-starter-test") {
        exclude(module = "junit")
        exclude(module = "mockito-core")
    }
    // https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-web
    implementation("org.springframework.boot:spring-boot-starter-web")
    // https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-data-rest
    implementation("org.springframework.boot:spring-boot-starter-data-rest")
    // https://mvnrepository.com/artifact/software.amazon.awssdk/rekognition
    implementation("software.amazon.awssdk:rekognition:2.17.202")
    // https://mvnrepository.com/artifact/io.github.microutils/kotlin-logging-jvm
    runtimeOnly("io.github.microutils:kotlin-logging-jvm:2.0.10")
    testImplementation("org.junit.jupiter:junit-jupiter-api")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
    // https://mvnrepository.com/artifact/io.mockk/mockk
    testImplementation("io.mockk:mockk:1.12.4")
    // https://mvnrepository.com/artifact/org.springframework.cloud/spring-cloud-starter-openfeign
    api("org.springframework.cloud:spring-cloud-starter-openfeign") {
        exclude(group = "org.springframework.cloud", module = "spring-cloud-netflix-ribbon")
    }
    // https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-data-jpa
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    // https://mvnrepository.com/artifact/org.postgresql/postgresql
    implementation("org.postgresql:postgresql")
    // https://mvnrepository.com/artifact/org.flywaydb/flyway-core
    implementation("org.flywaydb:flyway-core:8.5.13")
    // https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-actuator
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    // https://mvnrepository.com/artifact/org.springframework.cloud/spring-cloud-starter-bootstrap
    implementation("org.springframework.cloud:spring-cloud-starter-bootstrap")
    // https://mvnrepository.com/artifact/org.springframework.cloud/spring-cloud-starter-aws-parameter-store-config
    implementation("org.springframework.cloud:spring-cloud-starter-aws-parameter-store-config:2.2.6.RELEASE")
    // https://mvnrepository.com/artifact/com.fasterxml.jackson.dataformat/jackson-dataformat-xml
    implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-xml:2.13.3")
    // https://mvnrepository.com/artifact/com.fasterxml.jackson.module/jackson-module-kotlin
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.13.3")
    // https://mvnrepository.com/artifact/org.springframework.cloud/spring-cloud-starter-contract-stub-runner
    implementation("org.springframework.cloud:spring-cloud-starter-contract-stub-runner")
    // https://mvnrepository.com/artifact/org.springframework.cloud/spring-cloud-contract-wiremock
    implementation("org.springframework.cloud:spring-cloud-contract-wiremock")
    // https://mvnrepository.com/artifact/junit/junit
    testImplementation("junit:junit")
    // https://mvnrepository.com/artifact/org.mapstruct/mapstruct
    implementation("org.mapstruct:mapstruct:1.5.2.Final")
    // https://mvnrepository.com/artifact/org.mapstruct/mapstruct-processor
    kapt("org.mapstruct:mapstruct-processor:1.5.2.Final")
    // https://mvnrepository.com/artifact/com.h2database/h2
    testImplementation("com.h2database:h2")
    kapt("org.springframework.boot:spring-boot-configuration-processor")
    // https://mvnrepository.com/artifact/org.jetbrains.kotlin/kotlin-reflect
    runtimeOnly("org.jetbrains.kotlin:kotlin-reflect:1.7.10")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "18"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
