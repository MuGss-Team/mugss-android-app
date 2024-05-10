plugins {
    with(libs.plugins) {
        alias(kapt)
        alias(kotlin.serialization)
        alias(android.library)
        alias(kotlin.android)
    }
}

android {
    namespace = "com.mugss.core.network"
    compileSdk = libs.versions.compileSdk.get().toInt()
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    //Ktor
    with(libs.ktor.client) {
        implementation(core)
        implementation(android)
        implementation(serialization)
        implementation(logging.jvm)
        implementation(content.negotiation)
    }
    //Firebase
    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.auth)

    kapt(libs.hilt.compiler)
    implementation(libs.androidx.core.ktx)
    implementation(libs.hilt.android)
    implementation(libs.ktor.serialization.kotlinx.json)
    implementation(project(":core:data"))
}