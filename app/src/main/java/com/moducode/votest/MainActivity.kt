package com.moducode.votest


import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.moducode.votest.data.ConsolidatedWeather
import com.moducode.votest.data.WeatherRoot
import com.moducode.votest.schedulers.SchedulerProvider
import com.moducode.votest.service.RetrofitService
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.functions.Function
import timber.log.Timber
import java.text.SimpleDateFormat
import java.util.*

import javax.inject.Inject
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {

    private val listPOEIDS: List<String> = listOf("890869", "906057", "2455920", "44418", "2459115", "638242")

    @Inject
    lateinit var schedulers: SchedulerProvider

    @Inject
    lateinit var weatherService: RetrofitService

    override fun onCreate(savedInstanceState: Bundle?) {
        App.get(this).component.injectActivity(this)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onResume() {
        super.onResume()

        val calendar = Calendar.getInstance()
        calendar.add(Calendar.DAY_OF_YEAR, 1)

        val format = SimpleDateFormat("yyyy/MM/dd", Locale.US)

        Timber.d("EYYYY BOSS ${format.format(calendar.time)}")

        val test3 = Single.zip(getSingles(listPOEIDS)) {it.toList().filterIsInstance<WeatherRoot>()}
        test3.subscribe()


    }

    private fun getSingles(list: List<String>): List<Single<WeatherRoot>> = list.map {
        weatherService.fetchWOEIDWeather(it)
                .subscribeOn(schedulers.io())
                .observeOn(schedulers.ui())
                .doOnSuccess { result -> Timber.d(result.title) }
    }


}
