plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.example.andriodassignments"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.andriodassignments"
        minSdk = 30
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {


    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    implementation(libs.navigation.fragment)
    implementation(libs.navigation.ui)

    testImplementation(libs.junit)

   // New code added below
    testImplementation("junit:junit:4.13.2")
    testImplementation("org.mockito:mockito-core:4.0.0")
    testImplementation("org.mockito:mockito-inline:4.0.0")



    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
   // New code added below
    androidTestImplementation("androidx.test.espresso:espresso-intents:3.4.0")
    androidTestImplementation("androidx.test.espresso:espresso-contrib:3.4.0")


    // Add this if you use fragments
    androidTestImplementation("androidx.fragment:fragment-testing:1.3.6")

    // Add this if you want to use UI Automator
    androidTestImplementation("androidx.test.uiautomator:uiautomator:2.2.0")



}