package com.example.indyweather.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.indyweather.data.Resource
import com.example.indyweather.data.model.AQIData
import com.example.indyweather.data.model.AirQuality
import kotlinx.coroutines.launch

class DetailViewModel : ViewModel() {
    private val _airQuality = MutableLiveData<Resource<AirQuality>>()
    val airQuality: LiveData<Resource<AirQuality>> = _airQuality

    fun fetchAirQuality() {
        viewModelScope.launch {
            // Mock data
            val aqiData = AQIData(aqi = 50, pm10 = 20.0, pm25 = 12.5, o3 = 60.0, no2 = 15.0, so2 = 5.0, co = 0.6)
            val airQuality = AirQuality("Your City", "US", "State Code", listOf(aqiData))
            _airQuality.postValue(Resource.Success(airQuality))
        }
    }
}
