package com.project.weatherapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.project.weatherapp.databinding.ItemWeatherBinding
import com.project.weatherapp.model.WeatherResponse
import com.project.weatherapp.view.ListFragmentDirections
import kotlin.math.roundToInt

class WeatherAdapter(private val weather: MutableList<WeatherResponse> = mutableListOf()
) : RecyclerView.Adapter<WeatherAdapter.WeatherViewHolder>() {

    private lateinit var searchedCity: String

    class WeatherViewHolder(
        private val binding: ItemWeatherBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun loadWeather(item: WeatherResponse, city: String) = with(binding) {
            conditionText.text = item.weather?.get(0)?.main
            tempText.text = item.main?.temp?.roundToInt().toString()
            cardWeather.setOnClickListener {
                val action = ListFragmentDirections.actionListFragmentToDetailsFragment(city, item)
                it.findNavController().navigate(action)
            }
        }
        companion object {
            fun getInstance(parent: ViewGroup): WeatherViewHolder {
                val binding = ItemWeatherBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
                return WeatherViewHolder(binding)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
        = WeatherViewHolder.getInstance(parent)

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        val item = weather[position]
        holder.loadWeather(item, searchedCity)
    }

    override fun getItemCount() = weather.size

    fun clear() {
        val listSize = weather.size
        weather.clear()
        notifyItemRangeRemoved(0, listSize)
    }

    fun updateList(weatherList: List<WeatherResponse>, city: String) {
        searchedCity = city
        val positionStart = weather.size
        weather.addAll(weatherList)
        notifyItemRangeInserted(positionStart, weatherList.size)
    }
}