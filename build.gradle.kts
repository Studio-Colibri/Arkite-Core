plugins {
    id("java-library")
}

group = "kr.arkite.core"
version = "1.0.0"
description = "Arkite-Core Plugin"

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(21))
    }
    withSourcesJar()
}

repositories {
    mavenCentral()
    maven("https://repo.papermc.io/repository/maven-public/")
}

dependencies {
    compileOnly("io.papermc.paper:paper-api:1.21.4-R0.1-SNAPSHOT")
    // ModelEngine, ItemsAdder 등 추가 연동 플러그인도 필요시 compileOnly로 추가
}

tasks.jar {
    archiveFileName.set("Arkite-Core.jar")
    destinationDirectory.set(file("../../Build"))
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
    options.release.set(21)
}

tasks.withType<Javadoc> {
    options.encoding = "UTF-8"
}
