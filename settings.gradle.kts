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

rootProject.name = "KorotaevaHomework"
include(":app")
include(":shared:network")
include(":feature:enter")
include(":shared:navigation")
include(":shared:ui")
include(":feature:history")
include(":feature:main")
include(":utils")
include(":feature:details")
include(":feature:form")
include(":feature:screens")
