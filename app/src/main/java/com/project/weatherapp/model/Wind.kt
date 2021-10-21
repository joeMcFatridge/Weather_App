package com.project.weatherapp.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Wind(
    val speed: Float,
    val deg: Int,
    val gust: Float
)
