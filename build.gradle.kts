buildscript {
    repositories {
        jcenter()
    }
    dependencies {
        classpath("com.github.jengelman.gradle.plugins:shadow:5.2.0")
    }
}

plugins {
    kotlin("jvm") version "1.4.10"
    id("com.github.johnrengelman.shadow") version "5.2.0"
}

group = "com.IceCreamQAQ.bot.yuanShen"
version = "1.0-SNAPSHOT"

repositories {
    // Gradle 的 扫包是从上至下的，按照当前顺序，则为： 冰糕Luminous的仓库 -> 阿里云的仓库 -> 中央库 -> jcenter
    // YuQ-Mirai 的依赖位于中央库和 jcenter。

    mavenLocal()

    // 这是由 冰糕Luminous 提供的 Maven 仓库镜像。
    maven("https://oss.heavenark.com/repository/MavenPublic/")
    // 这是由 阿里云 提供的 Maven 仓库镜像。
    maven("https://maven.aliyun.com/repository/public")
    // 需要同时启用 中央库 及 jcenter。
    mavenCentral()
    jcenter()
    maven("https://maven.IceCreamQAQ.com/repository/maven-public/")
}

dependencies {
    implementation("com.IceCreamQAQ:YuQ:0.1.0.0-DEV3")
    implementation("com.IceCreamQAQ.YuQ:YuQ-Mirai:0.1.0.0-DEV2")
    implementation("com.IceCreamQAQ.Yu:Yu-DB:0.0.2.0-DEV2")
    implementation("com.h2database:h2:1.4.200")
}

tasks {

    jar {
        finalizedBy(shadowJar)
    }

    shadowJar {
        manifest {
            attributes["Main-Class"] = "com.IceCreamQAQ.bot.yuanShen.StartKt"
        }

        from("./") {
            include("build.gradle")
        }
    }
}