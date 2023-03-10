plugins {
    id("org.jetbrains.kotlin.multiplatform")
    id("org.jetbrains.compose")
}

version = "0.0.0"

kotlin {
    js(IR) {
        moduleName = "image-loader-bug"
        browser()
        binaries.executable()
    }

    sourceSets {
        getByName("commonMain") {
            dependencies {
                api(compose.animation)
                api(compose.foundation)
                api(compose.material)
                api(compose.runtime)
                api(compose.ui)
                api("io.github.qdsfdhvh:image-loader:1.2.9")
            }
        }
        getByName("jsMain") {
            dependencies {
                implementation(compose.web.core)
            }
        }
    }
}

compose.experimental {
    web.application {}
}
