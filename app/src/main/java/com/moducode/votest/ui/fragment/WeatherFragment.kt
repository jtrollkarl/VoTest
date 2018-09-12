package com.moducode.votest.ui.fragment

import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hannesdorfmann.mosby3.mvp.viewstate.MvpViewStateFragment
import com.moducode.votest.App
import com.moducode.votest.R
import com.moducode.votest.data.WeatherRoot
import com.moducode.votest.listPOEIDS
import com.moducode.votest.ui.adapter.WeatherRecyclerAdapter
import kotlinx.android.synthetic.main.fragment_forecasts.*

class WeatherFragment: MvpViewStateFragment<WeatherContract.View, WeatherContract.Actions, WeatherViewState>(), WeatherContract.View {

    private lateinit var weatherAdapter: WeatherRecyclerAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_forecasts, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        weatherAdapter = WeatherRecyclerAdapter(emptyList())
        val lm = LinearLayoutManager(context)
        val decoration = DividerItemDecoration(context, lm.orientation)

        recycler_forecasts.run {
            adapter = weatherAdapter
            addItemDecoration(decoration)
            layoutManager = lm
        }

    }

    override fun createPresenter(): WeatherContract.Actions = App.get(activity!!).component.buildWeatherPresenter()

    override fun createViewState(): WeatherViewState = WeatherViewState()

    override fun onNewViewStateInstance() {
        presenter.fetchForecasts(listPOEIDS)
    }

    override fun showForecasts(data: List<WeatherRoot>) {
        viewState.data = ArrayList<WeatherRoot>(data)
        weatherAdapter.data = data
        weatherAdapter.notifyDataSetChanged()
    }

}