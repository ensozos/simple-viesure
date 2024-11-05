
gradle.startParameter.excludedTaskNames.addAll(listOf(":build-logic:convention:testClasses"))
pluginManagement {
    includeBuild("build-logic")
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}


rootProject.name = "SimpleProject"
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
include(":app")
include(":feature:details")
include(":feature:list")
include(":core:data")
include(":core:domain")
include(":core:database")
include(":core:network")
include(":core:designsystem")
include(":core:model")
include(":core:testing")
