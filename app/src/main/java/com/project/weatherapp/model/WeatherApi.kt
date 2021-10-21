package com.project.weatherapp.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class WeatherApi(
    @Json(name = "cod")
    val cod: String,
    @Json(name = "message")
    val message: Any,
    @Json(name = "cnt")
    val cnt: Int,
    @Json(name = "list")
    val list: List<WeatherResponse>,
    @Json(name = "city")
    val city: City
)
