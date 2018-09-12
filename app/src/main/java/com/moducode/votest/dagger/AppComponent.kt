package com.moducode.votest.dagger

import com.moducode.votest.ui.fragment.WeatherPresenter
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [SchedulerModule::class, WeatherModule::class, ContextModule::class])
interface AppComponent {

    fun buildWeatherPresenter(): WeatherPresenter

}