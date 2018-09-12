package com.moducode.votest

import java.text.SimpleDateFormat
import java.util.*

val listPOEIDS: List<String> = listOf(
        "890869", //Gothenburg
        "906057", //Stockholm
        "2455920", //Mountain View
        "44418", // London
        "2459115", // New York
        "638242") //Berlin

val tomorrowDate: String
    get() {
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.DAY_OF_YEAR, 1)
        val format = SimpleDateFormat("yyyy-MM-dd", Locale.US)
        return format.format(calendar.time)
    }


