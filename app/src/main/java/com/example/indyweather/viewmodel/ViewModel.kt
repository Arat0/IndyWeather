package com.example.indyweather.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.indyweather.data.model.Weather
import com.example.indyweather.data.model.WeatherDescription
import com.example.indyweather.data.network.ApiService
import kotlinx.coroutines.launch

class ViewModel(private val apiService: ApiService) : ViewModel() {
    private val _weatherData = MutableLiveData<Weather>()
    val weatherData: LiveData<Weather> = _weatherData

    // Mock function to simulate fetching weather data
    fun fetchWeather() {
        viewModelScope.launch {
            // Mock data
            val mockWeatherDescription = WeatherDescription(icon = "sunny", code = 200, description = "Clear Sky")
            val mockWeather = Weather(
                latitude = 0.0,
                longitude = 0.0,
                timezone = "GMT",
                cityName = "Indianapolis",
                countryCode = "US",
                stateCode = "IN",
                temperature = 25.0,
                weather = mockWeatherDescription
            )
            _weatherData.postValue(mockWeather)
        }
    }

}
