package com.example.indyweather.data.model

import com.google.gson.annotations.SerializedName

data class WeatherForecast(
    @SerializedName("city_name") val cityName: String,
    @SerializedName("timezone") val timezone: String,
    @SerializedName("data") val forecastData: List<ForecastData>
)

data class ForecastData(
    @SerializedName("timestamp_local") val timestampLocal: String,
    @SerializedName("temp") val temperature: Double,
    @SerializedName("weather") val weather: WeatherDescription,  // Reusing WeatherDescription from Weather.kt
    @SerializedName("precip") val precipitation: Double,
    @SerializedName("snow") val snow: Double
)

