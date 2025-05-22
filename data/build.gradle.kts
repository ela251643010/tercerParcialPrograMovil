import org.gradle.internal.impldep.org.junit.experimental.categories.Categories.CategoryFilter.include
import org.jetbrains.kotlin.gradle.targets.js.npm.includedRange

plugins {
    id("java-library")
    alias(libs.plugins.jetbrains.kotlin.jvm)
}
java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}
kotlin {
    compilerOptions {
        jvmTarget = org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_11
    }
}
dependencies{
    implementation(project(":domain"))
}
