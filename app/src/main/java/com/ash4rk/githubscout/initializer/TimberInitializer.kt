package com.ash4rk.githubscout.initializer

import android.content.Context
import androidx.startup.Initializer
import timber.log.Timber

class TimberInitializer : Initializer<Unit> {

    override fun create(context: Context) {
        Timber.plant(Timber.DebugTree())
        Timber.d("Created TimberInitializer.")
    }

    override fun dependencies(): List<Class<out Initializer<*>>> = emptyList()
}
