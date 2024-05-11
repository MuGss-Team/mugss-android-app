plugins {
    with(libs.plugins) {
        alias(android.application)
        alias(kotlin.android)
        alias(hilt)
        alias(kapt)
        alias(kotlin.serialization)
        alias(google.google.services)
    }
}

android {
    namespace = "com.mugss.mugss"
    compileSdk = libs.versions.compileSdk.get().toInt()

    buildFeatures {
        buildConfig = true
    }

    defaultConfig {
        applicationId = "com.mugss.mugss"
        minSdk = libs.versions.minSdk.get().toInt()
        targetSdk = libs.versions.targetSdk.get().toInt()
        versionCode = 1
        versionName = "1.0"

        buildConfigField("String", "CLIENT_ID", "\"${System.getenv("CLIENT_ID")}\"")
        buildConfigField("String", "CLIENT_SECRET", "\"${System.getenv("CLIENT_SECRET")}\"")

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
            signingConfig = signingConfigs.getByName("debug")
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
        implementation(credentials)
        implementation(credentials.auth)

        //Compose
        implementation(activity.compose)
        implementation(platform(compose.bom))
        implementation(compose.ui)
        implementation(compose.material3)
        implementation(compose.ui.graphics)
        implementation(compose.ui.tooling.preview)
        implementation(navigation.compose)

        debugImplementation(compose.ui.tooling)
        debugImplementation(compose.ui.test.manifest)
    }
    testImplementation(libs.junit)

    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.auth)
    implementation(libs.firebase.firestore)
    implementation(libs.firebase.firestore)
    implementation(libs.google.id)

    implementation(project(":core:data"))
    implementation(project(":core:network"))
    implementation(project(":core:compose"))
    implementation(project(":core:navigation"))
}

// Allow references to generated code
kapt {
    correctErrorTypes = true
}