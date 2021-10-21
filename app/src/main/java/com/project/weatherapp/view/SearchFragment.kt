package com.project.weatherapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.project.weatherapp.databinding.FragmentSearchBinding
import com.project.weatherapp.util.Status
import com.project.weatherapp.viewmodel.WeatherViewModel

// TODO: 10/21/21 Localize strings
class SearchFragment : Fragment() {

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!
    private val weatherViewModel by activityViewModels<WeatherViewModel>()
    private lateinit var city: String


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentSearchBinding.inflate(layoutInflater, container, false).also {
        _binding = it
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initButton()
        initObserver()
    }

    private fun initButton() {
        binding.search.setOnClickListener {
            val searchText = binding.textSearch.text.toString()
            if (searchText.isNotEmpty()) {
                weatherViewModel.getData(searchText)
            } else {
                Toast.makeText(context, "Please fill out the city field to search.", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun initObserver() = with(weatherViewModel) {
        status.observe(viewLifecycleOwner) { state ->
            when (state) {
                    Status.LOST.stat -> {
                        Toast.makeText(context, "No city found.", Toast.LENGTH_LONG).show()
                    }
                    Status.GOOD.stat -> {
                        clearStatus()
                        city = searchedCity.toString()
                        val action = SearchFragmentDirections.actionSearchFragmentToListFragment(city)
                        findNavController().navigate(action)
                    }
                    Status.NEXT.stat -> {}
                    else -> {
                        Toast.makeText(context, "Network Error.", Toast.LENGTH_LONG).show()
                    }
            }
        }
    }
}