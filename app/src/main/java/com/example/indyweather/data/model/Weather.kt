package com.example.indyweather.data.model

import com.google.gson.annotations.SerializedName

data class Weather(
    @SerializedName("lat") val latitude: Double,
    @SerializedName("lon") val longitude: Double,
    @SerializedName("timezone") val timezone: String,
    @SerializedName("city_name") val cityName: String,
    @SerializedName("country_code") val countryCode: String,
    @SerializedName("state_code") val stateCode: String,
    @SerializedName("temp") val temperature: Double,
    @SerializedName("weather") val weather: WeatherDescription // Correctly reference the nested object
)

data class WeatherDescription(
    @SerializedName("icon") val icon: String,
    @SerializedName("code") val code: Int,
    @SerializedName("description") val description: String // Ensure description is available here
)
