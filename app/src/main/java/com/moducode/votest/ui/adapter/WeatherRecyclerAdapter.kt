package com.moducode.votest.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.moducode.votest.R
import com.moducode.votest.data.WeatherRoot
import kotlinx.android.synthetic.main.list_item_weather.view.*
import kotlin.math.roundToInt

class WeatherRecyclerAdapter(var data: List<WeatherRoot>): RecyclerView.Adapter<WeatherRecyclerAdapter.WeatherViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item_weather, parent, false)
        return WeatherViewHolder(view)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        holder.bind(data[position])
    }

    class WeatherViewHolder(listItem: View): RecyclerView.ViewHolder(listItem){

        fun bind(weather: WeatherRoot){
            itemView.tv_location.text = weather.title
            Glide.with(itemView)
                    .applyDefaultRequestOptions(RequestOptions().centerCrop())
                    .load("https://www.metaweather.com/static/img/weather/png/64/${weather.tomorrowWeather?.weatherStateAbbr}.png")
                    .into(itemView.iv_icon)

            itemView.tv_temp_high.text = itemView.context.getString(R.string.degrees_c, weather.tomorrowWeather?.maxTemp?.roundToInt())
            itemView.tv_temp_low.text = itemView.context.getString(R.string.degrees_c, weather.tomorrowWeather?.minTemp?.roundToInt())
        }

    }
}