plugins {
    id("com.android.application")
    id("com.google.gms.google-services")

}

android {

    namespace = "com.java.trainticketbookingapp"
    compileSdk = 34

    packagingOptions {
        resources {
            exclude("META-INF/NOTICE.md")
            exclude("META-INF/LICENSE.md")
        }
    }

    defaultConfig {
        applicationId = "com.java.trainticketbookingapp"
        minSdk = 26
        targetSdk = 33
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
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    implementation ("com.google.zxing:core:3.5.0")
    implementation ("com.google.code.gson:gson:2.10")
    implementation ("com.sun.mail:android-mail:1.6.6")
    implementation ("com.sun.mail:android-activation:1.6.6")
    implementation("com.squareup.picasso:picasso:2.5.2")
    implementation ("com.google.firebase:firebase-storage:20.3.0")
    implementation ("com.google.android.material:material:1.11.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("com.google.firebase:firebase-auth:22.3.0")
    implementation("com.google.android.gms:play-services-tasks:18.0.2")
    implementation("com.google.firebase:firebase-firestore:24.10.0")
    implementation("com.google.firebase:firebase-database:20.3.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    implementation(platform("com.google.firebase:firebase-bom:32.6.0"))
}

