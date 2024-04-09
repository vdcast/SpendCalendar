plugins {
    alias(libs.plugins.multiplatform)
    alias(libs.plugins.compose)
//    kotlin("multiplatform")
//    id("org.jetbrains.compose")
}

kotlin {
    jvm()

    sourceSets {
        commonMain {
            dependencies {
                implementation(compose.foundation)
                implementation(compose.runtime)
                implementation(compose.ui)
                implementation(compose.material)
            }
        }

        jvmMain {
            dependencies {
                api(compose.desktop.currentOs)
            }
        }
    }
}