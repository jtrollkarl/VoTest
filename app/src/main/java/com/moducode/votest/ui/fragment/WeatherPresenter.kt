package com.moducode.votest.ui.fragment

import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter
import com.moducode.votest.data.WeatherRoot
import com.moducode.votest.schedulers.SchedulerProvider
import com.moducode.votest.service.RetrofitService
import io.reactivex.Single
import timber.log.Timber
import javax.inject.Inject

class WeatherPresenter @Inject constructor(
        val weatherService: RetrofitService,
        val schedulers: SchedulerProvider)
    : MvpBasePresenter<WeatherContract.View>(), WeatherContract.Actions {


    override fun fetchForecasts(cityPOEIDS: List<String>) {
        Single.zip(getSingles(cityPOEIDS)) { it.toList().filterIsInstance<WeatherRoot>() }
                .subscribe(
                        { listResult ->
                            ifViewAttached { it.showForecasts(listResult) }
                        },
                        { e ->
                            Timber.e(e)
                            ifViewAttached { it.showError(e) }
                        }
                )

    }

    private fun getSingles(list: List<String>): List<Single<WeatherRoot>> = list.map { poeid ->
        weatherService.fetchWOEIDWeather(poeid)
                .subscribeOn(schedulers.io())
                .observeOn(schedulers.ui())
                .doOnSuccess { result -> Timber.d("${result.title} ${result.tomorrowWeather?.weatherStateName} ${result.tomorrowWeather?.applicableDate}")}
    }

}