package com.moducode.votest

import com.moducode.votest.schedulers.SchedulerProvider
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers

class TrampolineSchedulers: SchedulerProvider {

    override fun io(): Scheduler = Schedulers.trampoline()

    override fun compute(): Scheduler = Schedulers.trampoline()

    override fun ui(): Scheduler = Schedulers.trampoline()

}