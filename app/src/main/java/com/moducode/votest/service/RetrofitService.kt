package com.moducode.votest.service

import com.moducode.votest.data.WeatherRoot
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface RetrofitService {

    @GET("location/{woeid}/")
    fun fetchWOEIDWeather(@Path("woeid") id: String): Single<WeatherRoot>

}