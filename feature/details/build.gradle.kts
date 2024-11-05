plugins {
   alias(libs.plugins.simpleproject.android.feature)
   alias(libs.plugins.simpleproject.android.library.compose)
}

android {
    namespace = "com.zosimadis.details"
}

dependencies {
    implementation(projects.core.designsystem)
    implementation(projects.core.domain)
    implementation(projects.core.model)
    implementation(projects.core.data)

    implementation(libs.coil.kt.compose)

    testImplementation(libs.hilt.android.testing)
    testImplementation(libs.robolectric)
    testImplementation(projects.core.testing)

    androidTestImplementation(libs.bundles.androidx.compose.ui.test)
    androidTestImplementation(projects.core.testing)
}
