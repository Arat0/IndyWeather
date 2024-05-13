package com.example.indyweather.data.network

import com.example.indyweather.data.model.AirQuality
import com.example.indyweather.data.model.AlertResponse
import com.example.indyweather.data.model.Weather
import com.example.indyweather.data.model.WeatherForecast
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    // Fetch current weather data
    @GET("current")
    suspend fun getCurrentWeather(
        @Query("city") city: String,
        @Query("key") apiKey: String = "YOUR_API_KEY"
    ): Weather

    // Fetch weather forecast data
    @GET("forecast/daily")
    suspend fun getDailyForecast(
        @Query("city") city: String,
        @Query("days") days: Int,
        @Query("key") apiKey: String = "YOUR_API_KEY"
    ): WeatherForecast

    // Fetch air quality data
    @GET("airquality/current")
    suspend fun getCurrentAirQuality(
        @Query("city") city: String,
        @Query("key") apiKey: String = "YOUR_API_KEY"
    ): AirQuality

    interface ApiService {
        @GET("v2.0/alerts")
        suspend fun getSevereWeatherAlertsByLatLon(
            @Query("lat") latitude: Double,
            @Query("lon") longitude: Double,
            @Query("key") apiKey: String
        ): AlertResponse

        @GET("v2.0/alerts")
        suspend fun getSevereWeatherAlertsByCityName(
            @Query("city") city: String,
            @Query("state") state: String?,
            @Query("country") country: String?,
            @Query("key") apiKey: String
        ): AlertResponse

        @GET("v2.0/alerts")
        suspend fun getSevereWeatherAlertsByPostalCode(
            @Query("postal_code") postalCode: String,
            @Query("country") country: String?,
            @Query("key") apiKey: String
        ): AlertResponse

        @GET("v2.0/alerts")
        suspend fun getSevereWeatherAlertsByCityId(
            @Query("city_id") cityId: String,
            @Query("key") apiKey: String
        ): AlertResponse
    }}
