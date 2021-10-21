package com.project.weatherapp.repo

import com.project.weatherapp.model.WeatherApi
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {

    @GET("data/2.5/forecast")
    suspend fun getWeather(
        @Query("q") q: String,
        @Query("appid") appid: String = "65d00499677e59496ca2f318eb68c049",
        @Query("units") units: String = "imperial"
    ): Response<WeatherApi>
}