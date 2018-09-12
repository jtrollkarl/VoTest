package com.moducode.votest.data

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Source(
        @SerializedName("title") val title: String,
        @SerializedName("slug") val slug: String,
        @SerializedName("url") val url: String,
        @SerializedName("crawl_rate") val crawlRate: Int
): Parcelable