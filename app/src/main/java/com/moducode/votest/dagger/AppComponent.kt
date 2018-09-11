package com.moducode.votest.dagger

import com.moducode.votest.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [SchedulerModule::class, WeatherModule::class, ContextModule::class])
interface AppComponent {


    fun injectActivity(activity: MainActivity)

}