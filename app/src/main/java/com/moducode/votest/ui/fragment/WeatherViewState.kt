package com.moducode.votest.ui.fragment

import android.os.Bundle
import com.hannesdorfmann.mosby3.mvp.viewstate.RestorableViewState
import com.moducode.votest.data.WeatherRoot

class WeatherViewState: RestorableViewState<WeatherContract.View> {

    var data: List<WeatherRoot> = emptyList()

    companion object {
        private const val KEY_WEATHER_ROOT: String = "weather_key"
    }

    override fun saveInstanceState(outBundle: Bundle) {
        outBundle.putParcelableArrayList(KEY_WEATHER_ROOT, ArrayList(data))
    }

    override fun apply(view: WeatherContract.View?, retained: Boolean) {
        view?.showForecasts(data)
    }

    override fun restoreInstanceState(inBundle: Bundle?): RestorableViewState<WeatherContract.View> {
        data = inBundle?.getParcelableArrayList(KEY_WEATHER_ROOT) ?: emptyList()
        return this
    }
}