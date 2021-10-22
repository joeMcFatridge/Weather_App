package com.project.weatherapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.project.weatherapp.databinding.FragmentListBinding
import com.project.weatherapp.model.WeatherResponse
import com.project.weatherapp.adapter.WeatherAdapter
import com.project.weatherapp.viewmodel.WeatherViewModel
class ListFragment : Fragment() {

    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!
    private val weatherViewModel by activityViewModels<WeatherViewModel>()
    private val weatherAdapter by lazy { WeatherAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentListBinding.inflate(layoutInflater, container, false).also {
        _binding = it
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObserver()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun initObserver() = with(weatherViewModel) {
        weather.observe(viewLifecycleOwner) {
            if (it.isNotEmpty()) loadWeather(it)
        }
    }

    private fun loadWeather(weather: List<WeatherResponse>) = with(binding.rvWeather) {
        if(adapter == null) adapter = weatherAdapter
        val list = filterList(weather)
        weatherAdapter.clear()
        weatherAdapter.updateList(list, weatherViewModel.searchedCity.value.toString())
    }

    private fun filterList(list: List<WeatherResponse>): List<WeatherResponse> {
        val newList = mutableListOf<WeatherResponse>()
        var currentDate: String? = null
        var newDate: String?
        list.forEach lit@{ item ->
            if(currentDate.isNullOrBlank()) {
                currentDate = item.dtTxt?.take(10)
                newList.add(item)
                return@lit
            }
            newDate = item.dtTxt?.take(10)
            if (newDate == currentDate) {
                return@lit
            } else {
                currentDate = newDate
                newList.add(item)
            }
        }
        return newList
    }
}