package com.example.indyweather.data.repository

import com.example.indyweather.data.model.Weather
import com.example.indyweather.data.network.ApiService
import com.example.indyweather.data.Resource
import retrofit2.HttpException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class WeatherRepository(private val apiService: ApiService) {

    suspend fun getCurrentWeather(city: String, apiKey: String): Resource<Weather> {
        return withContext(Dispatchers.IO) {
            try {
                val response = apiService.getCurrentWeather(city, apiKey)
                if (response != null) {
                    Resource.Success(response)
                } else {
                    Resource.Error("No weather data found", null)
                }
            } catch (e: HttpException) {
                Resource.Error("Error fetching weather data: ${e.localizedMessage}", null)
            } catch (e: Throwable) {
                Resource.Error("An unexpected error occurred", null)
            }
        }
    }
}
