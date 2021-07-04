plugins {
    id("com.android.application")
    id("kotlin-android")
}

android {

    signingConfigs {
        create("signingConfigRelease") {
            storeFile = file(KeyHelper.getValue(KeyHelper.KEY_STORE_FILE))
            storePassword = KeyHelper.getValue(KeyHelper.KEY_STORE_PASS)
            keyAlias = KeyHelper.getValue(KeyHelper.KEY_ALIAS)
            keyPassword = KeyHelper.getValue(KeyHelper.KEY_PASS)
        }
    }

    defaultConfig {

        applicationId = Configs.applicationId
        minSdkVersion(Configs.minSdkVersion)
        targetSdkVersion(Configs.targetSdkVersion)
        versionCode = Configs.versionCode
        versionName = Configs.versionName

        compileSdkVersion(Configs.compileSkdVersion)
        buildToolsVersion(Configs.buildToolsVersion)

        signingConfig = signingConfigs.getByName("signingConfigRelease")

        testInstrumentationRunner("androidx.test.runner.AndroidJUnitRunner")
    }

    buildTypes {
        getByName("release") {
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
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }


    flavorDimensions("appType")
    productFlavors {
        create("_dev") {
            dimension = "appType"
            applicationIdSuffix = ".dev"
            versionNameSuffix = "-dev"

            manifestPlaceholders(
                mapOf(
                    "appIcon" to "@mipmap/ic_launcher_prod",
                    "appIconRound" to "@mipmap/ic_launcher_prod_round"
                )
            )
            resValue("string", "app_name", "KotlinDslExample$versionNameSuffix")
            buildConfigField("String", "ONESIGNAL_APP_ID", "\"${KeyHelper.KEY_ONESIGNAL_APP_ID_DEV}\"")
        }
        create("_test") {
            dimension = "appType"
            applicationIdSuffix = ".test"
            versionNameSuffix = "-test"

            manifestPlaceholders(
                mapOf(
                    "appIcon" to "@mipmap/ic_launcher_test",
                    "appIconRound" to "@mipmap/ic_launcher_test_round"
                )
            )
            resValue("string", "app_name", "KotlinDslExample$versionNameSuffix")
            buildConfigField("String", "ONESIGNAL_APP_ID", "\"${KeyHelper.KEY_ONESIGNAL_APP_ID_TEST}\"")
        }

        create("_prod") {
            dimension = "appType"
            applicationIdSuffix = ""
            versionNameSuffix = ""

            manifestPlaceholders(
                mapOf(
                    "appIcon" to "@mipmap/ic_launcher_prod",
                    "appIconRound" to "@mipmap/ic_launcher_prod_round"
                )
            )
            resValue("string", "app_name", "KotlinDslExample$versionNameSuffix")
            buildConfigField("String", "ONESIGNAL_APP_ID", "\"${KeyHelper.KEY_ONESIGNAL_APP_ID_PROD}\"")
        }
    }

    applicationVariants.all {
        val ext = when (flavorName) {
            "_dev" -> ".devUrlExt"
            "_test" -> ".testUrlExt"
            "_prod" -> ".prodUrlExt"
            else -> null
        }

        ext?.let {
            buildConfigField("String", "BASE_URL", "\"https://api.hostname$ext\"")
            /*
            other +30 buildConfigField
            ...
            ...
            ...
            */
        }
    }

}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))

    // Core
    implementation(Libraries.kotlinStdLib)
    implementation(Libraries.coreKtx)
    implementation(Libraries.appCompat)

    // Design
    implementation(Libraries.material)
    implementation(Libraries.constraintLayout)

    // Test
    testImplementation(Libraries.junit)
    androidTestImplementation(Libraries.junitTest)
    androidTestImplementation(Libraries.espressoCore)
}