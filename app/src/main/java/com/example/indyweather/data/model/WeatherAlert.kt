package com.example.indyweather.data.model

import com.google.gson.annotations.SerializedName

data class Alert(
    @SerializedName("title") val title: String,
    @SerializedName("description") val description: String,
    @SerializedName("severity") val severity: String,
    @SerializedName("effective_utc") val effectiveUtc: String,
    @SerializedName("expires_utc") val expiresUtc: String
)

data class AlertResponse(
    @SerializedName("alerts") val alerts: List<Alert>
)
