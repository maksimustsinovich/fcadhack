plugins {
    java
    id("org.springframework.boot") version "3.3.4"
    id("io.spring.dependency-management") version "1.1.6"
}

group = "by.ustsinovich"
version = "0.0.1-SNAPSHOT"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.projectlombok:lombok")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    runtimeOnly("org.postgresql:postgresql")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    compileOnly("org.mapstruct:mapstruct:1.5.3.Final")
    annotationProcessor("org.mapstruct:mapstruct-processor:1.5.3.Final")
    annotationProcessor("org.projectlombok:lombok-mapstruct-binding:0.2.0")
}

tasks.withType<Test> {
    useJUnitPlatform()
}
