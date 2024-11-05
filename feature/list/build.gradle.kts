plugins {
    alias(libs.plugins.simpleproject.android.feature)
    alias(libs.plugins.simpleproject.android.library.compose)
}

android {
    namespace = "com.zosimadis.list"
}

dependencies {

    implementation(projects.core.model)
    implementation(projects.core.domain)
    implementation(projects.core.data)
    implementation(projects.feature.details)

    implementation(libs.coil.kt.compose)

    testImplementation(projects.core.testing)
    testImplementation(libs.robolectric)

    androidTestImplementation(libs.bundles.androidx.compose.ui.test)
    androidTestImplementation(projects.core.testing)

}