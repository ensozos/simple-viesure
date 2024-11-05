plugins {
    alias(libs.plugins.simpleproject.android.library)
    alias(libs.plugins.simpleproject.android.hilt)
    id("kotlinx-serialization")
}

android {
    namespace = "com.zosimadis.data"

    testOptions {
        unitTests {
            isIncludeAndroidResources = true
        }
    }
}

dependencies {
    api(projects.core.database)
    api(projects.core.network)
    api(projects.core.model)

    implementation(libs.kotlinx.coroutines.core)

    testImplementation(libs.androidx.test.coroutines)
    testImplementation(projects.core.testing)
}