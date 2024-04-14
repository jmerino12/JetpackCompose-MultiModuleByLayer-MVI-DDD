pluginManagement {
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

rootProject.name = "Movie"
include(":app")
include(":modules:movie:domain")
include(":modules:movie:infrastructure")
include(":modules:movie:ui")
include(":modules:auth:domain")
include(":modules:auth:infrastructure")
include(":modules:auth:ui")
include(":core:network")
include(":core:theme")

include(":core:database")
