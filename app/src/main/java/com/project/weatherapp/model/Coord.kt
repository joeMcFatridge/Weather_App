package com.project.weatherapp.model

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class Coord(
    val lat: Float,
    val lon: Float
): Parcelable
