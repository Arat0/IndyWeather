package com.example.indyweather.data.repository

import com.example.indyweather.data.model.AirQuality
import com.example.indyweather.data.network.ApiService
import com.example.indyweather.data.Resource
import retrofit2.HttpException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AirQualityRepository(private val apiService: ApiService) {

    suspend fun getCurrentAirQuality(city: String, apiKey: String): Resource<AirQuality> {
        return withContext(Dispatchers.IO) {
            try {
                val response = apiService.getCurrentAirQuality(city, apiKey)
                if (response.aqiData.isNotEmpty()) { // Check if the data array is not empty
                    Resource.Success(response)
                } else {
                    Resource.Error("No air quality data found", null)
                }
            } catch (e: HttpException) {
                Resource.Error("Error fetching air quality data: ${e.response()?.errorBody()?.string() ?: e.localizedMessage}", null)
            } catch (e: Throwable) {
                Resource.Error("An unexpected error occurred: ${e.localizedMessage}", null)
            }
        }
    }
}
