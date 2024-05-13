package com.example.indyweather.data.network

import com.example.indyweather.data.model.Weather
import com.example.indyweather.data.model.AirQuality
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {

    @GET("v2.0/current")
    suspend fun getCurrentWeather(
        @Query("city") city: String,
        @Query("key") apiKey: String
    ): Weather

    @GET("v2.0/current/air-quality")
    suspend fun getCurrentAirQuality(
        @Query("city") city: String,
        @Query("key") apiKey: String
    ): AirQuality
}
