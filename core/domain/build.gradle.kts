plugins {
    alias(libs.plugins.simpleproject.android.library)
    id("com.google.devtools.ksp")
}

android {
    namespace = "com.zosimadis.domain"
}

dependencies {
    api(projects.core.data)
    api(projects.core.model)

    implementation(libs.javax.inject)
    implementation(libs.kotlinx.coroutines.core)

    testImplementation(projects.core.testing)
}