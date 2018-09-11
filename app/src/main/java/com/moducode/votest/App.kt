package com.moducode.votest

import android.app.Activity
import android.app.Application
import com.moducode.votest.dagger.AppComponent
import com.moducode.votest.dagger.ContextModule
import com.moducode.votest.dagger.DaggerAppComponent
import timber.log.Timber

class App: Application() {

    lateinit var component: AppComponent

    companion object {
        fun get(context: Activity): App = context.application as App
    }

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())

        component = DaggerAppComponent
                .builder()
                .contextModule(ContextModule(this))
                .build()
    }
}