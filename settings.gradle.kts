enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
        maven(url = "https://oss.sonatype.org/content/repositories/snapshots/")
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
        maven(url = "https://oss.sonatype.org/content/repositories/snapshots/")
    }
}
rootProject.name = "MyDroidGitExp"
include(":app")
include(":core")