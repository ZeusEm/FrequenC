buildscript {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
    dependencies {
        // Android Gradle Plugin (AGP) version
        classpath("com.android.tools.build:gradle:8.8.1")

        // Kotlin Gradle Plugin (must match Compose Compiler requirement)
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.9.22")

        // Hilt Gradle Plugin
        classpath("com.google.dagger:hilt-android-gradle-plugin:2.50")
    }
}

// No 'plugins' block here!
// We'll apply them in the app module's build.gradle.kts