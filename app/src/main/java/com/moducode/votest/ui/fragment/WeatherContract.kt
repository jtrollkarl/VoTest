package com.moducode.votest.ui.fragment

import com.hannesdorfmann.mosby3.mvp.MvpPresenter
import com.hannesdorfmann.mosby3.mvp.MvpView
import com.moducode.votest.data.WeatherRoot

interface WeatherContract {

    interface View : MvpView {
        fun showForecasts(data: List<WeatherRoot>)
    }

    interface Actions : MvpPresenter<View> {
        fun fetchForecasts(cityPOEIDS: List<String>)
    }
}