package com.project.weatherapp.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.weatherapp.repo.ApiClient
import com.project.weatherapp.model.WeatherResponse
import com.project.weatherapp.util.Status
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*

class WeatherViewModel: ViewModel() {

    private val _weather = MutableLiveData<List<WeatherResponse>>()
    val weather: LiveData<List<WeatherResponse>> get() = _weather

    private val _status = MutableLiveData<String>()
    val status: LiveData<String> get() = _status

    private val _searchedCity = MutableLiveData<String>()
    val searchedCity: LiveData<String> get() = _searchedCity


    fun getData(city: String) {
        _searchedCity.value = city.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }
        fetchWeather()
    }

    fun clearStatus() {
        viewModelScope.launch {
            setStatus(Status.NEXT.stat)
        }
    }

    private fun fetchWeather() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = ApiClient.weatherService.getWeather(searchedCity.value.toString())

                if(response.isSuccessful) {
                    setStatus(Status.GOOD.stat)
                    response.body().also {
                        it?.list?.let { data -> setData(data) }
                    }
                } else {
                    setStatus(Status.LOST.stat)
                }
            }
            catch (e: Exception) {
                setStatus(Status.BAD.stat)
                Log.e("VIEWMODEL", "Error Occurred ${e.message}")
            }
        }
    }

    private suspend fun setData(data: List<WeatherResponse>) {
        withContext(Dispatchers.Main) {
            _weather.value = data
        }
    }

    private suspend fun setStatus(status: String) {
        withContext(Dispatchers.Main) {
            _status.value = status
        }
    }
}