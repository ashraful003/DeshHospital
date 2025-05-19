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
    namespace = "com.myapp.deshhospital"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.myapp.deshhospital"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    buildFeatures {
        //noinspection DataBindingWithoutKapt
        dataBinding = true
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    implementation(libs.filament.android)
    implementation(libs.impress)
    //navigation graph
    implementation(libs.navigation.fragment.ktx)
    implementation(libs.navigation.ui.ktx)
    implementation(libs.navigation.dynamic.features)
    //dagger hilt
    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)
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