package com.project.weatherapp.model

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class Main(
    val temp: Float,
    @Json(name = "feels_like")
    val feelsLike: Float,
    @Json(name = "temp_min")
    val tempMin: Float,
    @Json(name = "temp_max")
    val tempMax: Float,
    val pressure: Int,
    @Json(name = "sea_level")
    val seaLevel: Int,
    @Json(name = "grnd_level")
    val grndLevel: Int,
    val humidity: Int,
    @Json(name = "temp_kf")
    val tempKf: Float
): Parcelable
