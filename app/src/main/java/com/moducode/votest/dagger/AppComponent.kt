package com.moducode.votest.dagger

import dagger.Component


@Component(modules = [SchedulerModule::class, WeatherService::class, ContextModule::class])
interface AppComponent {

    


}