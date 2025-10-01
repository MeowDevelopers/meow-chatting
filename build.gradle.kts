plugins {
    kotlin("jvm") version "1.9.25"
    kotlin("plugin.spring") version "1.9.25"
    kotlin("plugin.jpa") version "1.9.25"      // kotlin-jpa 플러그인 추가 (엔티티를 open으로 처리)
    kotlin("kapt") version "1.9.25"           // kapt 플러그인: annotation processing (QueryDSL 등)

    id("org.springframework.boot") version "3.5.4"
    id("io.spring.dependency-management") version "1.1.7"

    id("org.flywaydb.flyway") version "7.2.0"

    java
}

group = "com.meow"
version = "0.0.1-SNAPSHOT"
description = "meow-chatting"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

repositories {
    mavenCentral()
}

val querydslVersion = "5.0.0"

dependencies {
    // Spring Boot
    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.boot:spring-boot-starter-validation")

    // Kotlin
    implementation("org.jetbrains.kotlin:kotlin-reflect")

    // Java
    compileOnly("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")

    // TEST
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")

    implementation("org.springframework.boot:spring-boot-starter-security")

    // Redis
    implementation("org.springframework.boot:spring-boot-starter-data-redis")
    implementation ("io.netty:netty-resolver-dns-native-macos:4.1.72.Final:osx-aarch_64")

    // MySQL
    implementation("com.mysql:mysql-connector-j:8.2.0")

    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("com.querydsl:querydsl-jpa:$querydslVersion:jakarta")
    kapt("com.querydsl:querydsl-apt:$querydslVersion:jakarta")
    kapt("jakarta.annotation:jakarta.annotation-api")
    kapt("jakarta.persistence:jakarta.persistence-api")

    implementation("io.jsonwebtoken:jjwt-api:0.12.3")
    runtimeOnly("io.jsonwebtoken:jjwt-impl:0.12.3")
    runtimeOnly("io.jsonwebtoken:jjwt-jackson:0.12.3")
}

kotlin {
    compilerOptions {
        freeCompilerArgs.addAll("-Xjsr305=strict")
    }
}

flyway {
    url = "jdbc:mysql://127.0.0.1:3400/meow?useSSL=false&allowPublicKeyRetrieval=true"
    user = "meow"
    password = "1234qwer"
}


tasks.withType<Test> {
    useJUnitPlatform()
}

val generated = file("src/main/generated")

tasks.withType<JavaCompile> {
    options.generatedSourceOutputDirectory.set(generated)
}
// kotlin source set에 querydsl QClass 위치 추가
sourceSets {
    main {
        kotlin.srcDirs += generated
    }
}

// gradle clean 시에 QClass 디렉토리 삭제
tasks.named("clean") {
    doLast {
        generated.deleteRecursively()
    }
}
