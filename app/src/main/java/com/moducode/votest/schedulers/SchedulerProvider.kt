package com.moducode.votest.schedulers

import io.reactivex.Scheduler


interface SchedulerProvider {

    fun io(): Scheduler

    fun compute(): Scheduler

    fun ui(): Scheduler

}