plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.kapt)
    alias(libs.plugins.hilt.android)
}
kapt {
    correctErrorTypes = true
}
android {
    namespace = "com.myapp.medicinestore"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.myapp.medicinestore"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
    buildFeatures {
        //noinspection DataBindingWithoutKapt
        dataBinding = true
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.filament.android)
    implementation(libs.impress)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    //dagger hilt
    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)
    //navigation graph
    implementation(libs.navigation.fragment.ktx)
    implementation(libs.navigation.ui.ktx)
    implementation(libs.navigation.dynamic.features)
    implementation(libs.viewpager2)
    implementation(libs.cardview)
    implementation(libs.rxjava)
    implementation(libs.rxbinding)

    implementation(libs.core.ktx)
    implementation(libs.appcompat)
    implementation(libs.constraintlayout)
    implementation(libs.shimmer)
    implementation(libs.glide)
    implementation(libs.gson)
    implementation(libs.legacy.support.v4)
    implementation(libs.lifecycle.livedata.ktx)
    implementation(libs.lifecycle.viewmodel.ktx)
    implementation(libs.circle.imageview)
    androidTestImplementation(libs.espresso.core)
}