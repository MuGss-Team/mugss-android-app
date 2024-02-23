plugins {
    with(libs.plugins) {
        alias(android.application)
        alias(kotlin.android)
        alias(hilt)
        alias(kapt)
        alias(kotlin.serialization)
    }
}

android {
    namespace = "com.example.mugss"
    compileSdk = libs.versions.compileSdk.get().toInt()

    defaultConfig {
        applicationId = "com.example.mugss"
        minSdk = libs.versions.minSdk.get().toInt()
        targetSdk = libs.versions.targetSdk.get().toInt()
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
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
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    //Desugaring
    coreLibraryDesugaring(libs.core.jdk.desugaring)

    //Coroutines
    implementation(libs.kotlinx.coroutines.android)

    //DI
    implementation(libs.hilt.android)
    implementation(libs.androidx.hilt.navigation.compose)
    kapt(libs.hilt.compiler)

    //Coil
    implementation(libs.coil.kt.compose)

    //Navigation
    implementation(libs.androidx.navigation.compose)

    //Ktor
    with(libs.ktor.client) {
        implementation(core)
        implementation(android)
        implementation(serialization)
        implementation(logging.jvm)
        implementation(content.negotiation)
    }
    implementation(libs.ktor.serialization.kotlinx.json)


    //kotlinx.serialization
    implementation(libs.kotlinx.serialization.json)

    with(libs.androidx) {
        implementation(core.ktx)
        implementation(lifecycle.runtime)
        implementation(lifecycle.runtime.compose)
        implementation(lifecycle.viewModelCompose)

        //Compose
        val composeBom = platform(compose.bom)
        implementation(activity.compose)
        implementation(composeBom)
        implementation(compose.ui)
        implementation(compose.ui.graphics)
        implementation(compose.ui.tooling.preview)
        implementation(compose.material3)

        androidTestImplementation(test.ext.junit)
        androidTestImplementation(test.espresso.core)
        androidTestImplementation(composeBom)
        androidTestImplementation(compose.ui.test.junit4)
        debugImplementation(compose.ui.tooling)
        debugImplementation(compose.ui.test.manifest)
    }
    testImplementation(libs.junit)
}

// Allow references to generated code
kapt {
    correctErrorTypes = true
}