package com.project.weatherapp.model

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
@JsonClass(generateAdapter = true)
data class WeatherResponse(
    val dt: String?,
    val main: Main?,
    val weather: List<Weather>?,
    val clouds: Clouds?,
    val wind: @RawValue Wind?,
    val visibility: Int?,
    val pop: Float?,
    val sys: Sys?,
    @Json(name = "dt_txt")
    val dtTxt: String?
): Parcelable


