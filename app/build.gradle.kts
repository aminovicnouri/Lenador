plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.aminovic.lenador"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.aminovic.lenador"
        minSdk = 26
        targetSdk = 33
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.3"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    val navigationComposeVersion = "2.7.0"
    val lifecycleRuntimeComposeVersion = "2.6.1"
    val lifecycleRuntimeViewmodelComposeVersion = "2.6.1"
    val hiltAndroidVersion = "2.45"
    val hiltCompilerVersion = "1.0.0"
    val moshiKotlinVersion = "1.15.0"
    val roomKtxVersion = "2.5.2"
    val gsonVersion = "2.10.1"
    val kotlinxCoroutinesCoreVersion = "1.7.1"
    val kotlinxCoroutinesAndroidVersion = "1.7.1"

    val lifecycleViewmodelKtxVersion = "2.6.1"
    val lifecycleRuntimeKtxVersion = "2.6.1"
    val workRuntimeKtxVersion = "2.8.1"

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")
    implementation("androidx.activity:activity-compose:1.8.1")
    implementation(platform("androidx.compose:compose-bom:2023.03.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.03.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")

    implementation("androidx.navigation:navigation-compose:$navigationComposeVersion")
    implementation("androidx.lifecycle:lifecycle-runtime-compose:$lifecycleRuntimeComposeVersion")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:$lifecycleRuntimeViewmodelComposeVersion")

    implementation ("androidx.compose.material:material-icons-extended:1.4.3")
    implementation("com.seanproctor:data-table-material3:0.5.1")



    // Dagger - Hilt
    implementation("com.google.dagger:hilt-android:$hiltAndroidVersion")
    kapt("com.google.dagger:hilt-android-compiler:$hiltAndroidVersion")
    kapt("androidx.hilt:hilt-compiler:$hiltCompilerVersion")
    implementation("androidx.hilt:hilt-navigation-compose:$hiltCompilerVersion")
    implementation("androidx.hilt:hilt-work:$hiltCompilerVersion")

    // Room
    implementation("androidx.room:room-ktx:$roomKtxVersion")
    kapt("androidx.room:room-compiler:$roomKtxVersion")
    implementation("androidx.room:room-paging:$roomKtxVersion")
    implementation("com.google.code.gson:gson:$gsonVersion")
    implementation("com.squareup.moshi:moshi-kotlin:$moshiKotlinVersion")

    // Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$kotlinxCoroutinesCoreVersion")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:$kotlinxCoroutinesAndroidVersion")

    // Coroutine Lifecycle Scopes
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycleViewmodelKtxVersion")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:$lifecycleRuntimeKtxVersion")
    implementation("androidx.work:work-runtime-ktx:$workRuntimeKtxVersion")

}

// Allow references to generated code
kapt {
    correctErrorTypes = true
}