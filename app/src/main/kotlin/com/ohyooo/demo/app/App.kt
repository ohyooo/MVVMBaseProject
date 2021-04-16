package com.ohyooo.demo.app

import android.app.Application
import timber.log.Timber

class App : Application() {
    init {
        instance = this
    }

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
    }

    companion object {
        lateinit var instance: App
            private set
    }
}