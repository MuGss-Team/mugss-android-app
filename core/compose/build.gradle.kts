plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.mugss.core.compose"
    compileSdk = libs.versions.compileSdk.get().toInt()
    defaultConfig {
        minSdk = libs.versions.minSdk.get().toInt()
        testOptions.targetSdk = libs.versions.minSdk.get().toInt()
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.compose.compiler.get()
    }
}

dependencies {
    with(libs.androidx.compose) {
        implementation(platform(bom))
        implementation(ui)
        implementation(runtime)
        implementation(ui.graphics)
        implementation(material3)
        implementation(ui.tooling.preview)
        debugImplementation(ui.tooling)
        debugImplementation(ui.test.manifest)
    }
}