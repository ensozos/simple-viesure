plugins {
    alias(libs.plugins.simpleproject.android.library)
    alias(libs.plugins.simpleproject.android.room)
    alias(libs.plugins.simpleproject.android.hilt)
}

android {
    namespace = "com.zosimadis.database"
}

dependencies {
    api(projects.core.model)
}