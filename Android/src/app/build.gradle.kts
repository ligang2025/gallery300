import com.android.build.api.variant.ApplicationVariant

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {

    namespace = "com.google.ai.edge.gallery"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.google.ai.edge.gallery"
        minSdk = 24
        targetSdk = 34

        versionCode = 1
        versionName = "1.0.0"
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
}

androidComponents {
    onVariants { variant ->
        variant.outputs.forEach { output ->
            val versionName = variant.versionName.getOrElse("1.0.0")
            val buildTime = java.time.LocalDateTime.now()
                .toString()
                .replace(":", "-")
            output.outputFileName.set(
                "Gallery-${variant.name}-v${versionName}-${buildTime}.apk"
            )
        }
    }
}
