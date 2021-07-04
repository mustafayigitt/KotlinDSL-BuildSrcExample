object Libraries {

    // Classpath Gradle Plugin
    const val classpathGradle =
        "com.android.tools.build:gradle:${Versions.classpathGradleVersion}"
    const val classpathKotlinGradle =
        "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlinVersion}"

    // Core
    const val kotlinStdLib =
        "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlinVersion}"
    const val coreKtx = "androidx.core:core-ktx:${Versions.coreKtxVersion}"
    const val appCompat = "androidx.appcompat:appcompat:${Versions.appCompatVersion}"

    // Design
    const val constraintLayout =
        "androidx.constraintlayout:constraintlayout:${Versions.constraintLayoutVersion}"
    const val material = "com.google.android.material:material:${Versions.materialVersion}"

    // Test
    const val junit = "junit:junit:${Versions.junitVersion}"
    const val junitTest = "androidx.test.ext:junit:${Versions.junitTestVersion}"
    const val espressoCore = "androidx.test.espresso:espresso-core:${Versions.espressoCoreVersion}"
}