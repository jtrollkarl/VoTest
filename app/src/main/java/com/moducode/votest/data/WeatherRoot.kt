package com.moducode.votest.data

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.moducode.votest.tomorrowDate
import kotlinx.android.parcel.Parcelize


@Parcelize
data class WeatherRoot(
        @SerializedName("consolidated_weather") val consolidatedWeather: List<ConsolidatedWeather>,
        @SerializedName("time") val time: String,
        @SerializedName("sun_rise") val sunRise: String,
        @SerializedName("sun_set") val sunSet: String,
        @SerializedName("timezone_name") val timezoneName: String,
        @SerializedName("parent") val parent: Parent,
        @SerializedName("sources") val sources: List<Source>,
        @SerializedName("title") val title: String,
        @SerializedName("location_type") val locationType: String,
        @SerializedName("woeid") val woeid: Int,
        @SerializedName("latt_long") val lattLong: String,
        @SerializedName("timezone") val timezone: String
): Parcelable{

    val tomorrowWeather: ConsolidatedWeather?
    get() {
        return consolidatedWeather.filter { it.applicableDate == tomorrowDate }.maxBy { it.predictability }
    }




}