package com.moducode.votest.ui.fragment

import com.moducode.votest.TrampolineSchedulers
import com.moducode.votest.data.WeatherRoot
import com.moducode.votest.listPOEIDS
import com.moducode.votest.service.RetrofitService
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.verify
import io.reactivex.Single
import org.junit.Test
import org.junit.Before

class WeatherPresenterTest {

    @MockK
    private lateinit var weatherService: RetrofitService

    @RelaxedMockK
    private lateinit var view: WeatherContract.View

    @RelaxedMockK
    private lateinit var mockData: WeatherRoot

    private lateinit var subject: WeatherPresenter

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        subject = WeatherPresenter(weatherService, TrampolineSchedulers())
        subject.attachView(view)
    }

    @Test
    fun fetchForecasts_SUCCESS() {
        every { weatherService.fetchWOEIDWeather(any<String>()) } returns Single.just(mockData)
        subject.fetchForecasts(listPOEIDS)

        verify { view.showForecasts(List(listPOEIDS.size) {mockData}) }
    }

    @Test
    fun fetchForecasts_FAIL(){
        val e = Throwable("Error!")

        every { weatherService.fetchWOEIDWeather(any<String>()) } returns Single.error(e)
        subject.fetchForecasts(listPOEIDS)
        verify { view.showError(e) }

    }

}