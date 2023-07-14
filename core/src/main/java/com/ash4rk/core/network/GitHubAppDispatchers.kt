package com.ash4rk.core.network

import javax.inject.Qualifier
import kotlin.annotation.AnnotationRetention.RUNTIME

@Qualifier
@Retention(RUNTIME)
annotation class Dispatcher(val githubAppDispatchers: GitHubAppDispatchers)

enum class GitHubAppDispatchers {
    IO
}
