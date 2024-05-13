package com.example.indyweather.data.model

import com.google.gson.annotations.SerializedName

data class AirQuality(
    @SerializedName("city_name") val cityName: String,
    @SerializedName("country_code") val countryCode: String,
    @SerializedName("state_code") val stateCode: String,
    @SerializedName("data") val aqiData: List<AQIData>
)

data class AQIData(
    @SerializedName("aqi") val aqi: Int,
    @SerializedName("pm10") val pm10: Double,
    @SerializedName("pm25") val pm25: Double,
    @SerializedName("o3") val o3: Double,
    @SerializedName("no2") val no2: Double,
    @SerializedName("so2") val so2: Double,
    @SerializedName("co") val co: Double
)
