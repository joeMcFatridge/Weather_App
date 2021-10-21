package com.project.weatherapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.project.weatherapp.databinding.FragmentDetailsBinding
import com.project.weatherapp.model.WeatherResponse
import kotlin.math.roundToInt

class DetailsFragment() : Fragment() {

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!
    private val args by navArgs<DetailsFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentDetailsBinding.inflate(layoutInflater, container, false).also {
        _binding = it
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val weather: WeatherResponse = args.weather
        with(binding) {
            detailsTemp.text = weather.main?.temp?.roundToInt().toString()
            detailsFeelsLike.text = weather.main?.feelsLike?.roundToInt().toString()
            detailsWeather.text = weather.weather?.first()?.main
            detailsDescription.text  = weather.weather?.first()?.description
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}