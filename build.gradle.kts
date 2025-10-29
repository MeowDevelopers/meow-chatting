plugins {
    kotlin("jvm") version "1.9.25"
    kotlin("plugin.spring") version "1.9.25"
    kotlin("plugin.jpa") version "1.9.25"
    kotlin("kapt") version "1.9.25"

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

dependencies {
    // Spring Boot
    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-webflux")

    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation ("org.springframework.boot:spring-boot-starter-jdbc")
    implementation ("com.h2database:h2")
    implementation("org.springframework.boot:spring-boot-starter-validation")

    // Kotlin
    implementation("org.jetbrains.kotlin:kotlin-reflect")

    // Java
    compileOnly("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")

    // QueryDSL
    implementation("com.querydsl:querydsl-jpa:5.0.0:jakarta")
    kapt("com.querydsl:querydsl-apt:5.0.0:jakarta")
    kapt("jakarta.annotation:jakarta.annotation-api")
    kapt("jakarta.persistence:jakarta.persistence-api")

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

    // SNS 로그인
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.security:spring-security-oauth2-client")

    implementation("io.jsonwebtoken:jjwt-api:0.12.3")
    runtimeOnly("io.jsonwebtoken:jjwt-impl:0.12.3")
    runtimeOnly("io.jsonwebtoken:jjwt-jackson:0.12.3")

    // WebSocket
    implementation("org.springframework.boot:spring-boot-starter-websocket")
}

kotlin {
    compilerOptions {
        freeCompilerArgs.addAll("-Xjsr305=strict")
    }
}

// QueryDSL
kapt {
    keepJavacAnnotationProcessors = true

    arguments {
        arg("querydsl.entityAccessors", "true")  // getter/setter 대신 필드 직접 접근
        arg("querydsl.useFields", "true")
    }

    // Q 클래스 경로
    javacOptions {
        option("querydsl.generatedAnnotationClass", "javax.annotation.processing.Generated")
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

// Java 컴파일러가 Kotlin 소스와 Java 소스를 함께 인식하도록 지정
sourceSets {
    main {
        java {
            setSrcDirs(listOf("src/main/java", "src/main/kotlin"))
        }
    }
}
